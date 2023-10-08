package com.example.apinasa

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), LifecycleOwner {

    companion object{
        var MY_CHANNEL_ID = "1"
    }
    private lateinit var itUsername: EditText
    private lateinit var itPassword: EditText
    private lateinit var itUserNameRegister:EditText
    private lateinit var itPassworRegister:EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister:Button
    private lateinit var toolbar: Toolbar
    private lateinit var check:CheckBox
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createChannel()
        init()
    }


    private fun init() {
        //consultar preference si existe algo logueo directo.. sino sigo con el codigo
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
       //obtener prefers y loguear si existe
        val name = sharedPreferences.getString("userName","")
        val pass  = sharedPreferences.getString("password","")

        if(name != "" && pass != ""){
            val intent= Intent(this,Menu::class.java)
            startActivity(intent)
            finish()
        }


        // Asignar referencias de las vistas a las variables
        //login
        itUsername = findViewById(R.id.itUserNameLogin)
        itPassword = findViewById(R.id.itPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        check=findViewById(R.id.checkRecorder)
        //register
        itUserNameRegister=findViewById(R.id.itUserNameRegister)
        itPassworRegister=findViewById(R.id.itPasswordRegister)
        btnRegister=findViewById(R.id.btnRegister)

        //recuperamos el toolbar
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title=resources.getString(R.string.app_name)



        btnLogin.setOnClickListener {
            val userName=itUsername.text.toString()
            val password=itPassword.text.toString()

            if(userName!=""&&password!=""){
               //verofoca si existe en bd
                if(verificarUsuario(userName,password)){
                    //si existe lo logueo y si puse recuerdame lo guardo
                    if(check.isChecked){

                        lifecycleScope.launch(Dispatchers.IO){
                            //seteamos por defecto en el preference
                            sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            sharedPreferences.edit().putString("userName",userName).apply()
                            sharedPreferences.edit().putString("password",password).apply()
                            createSimpleNotification()
                        }
                        Toast.makeText(this, "usuario recordado", Toast.LENGTH_SHORT).show()

                        //logueo
                        val intent=Intent(this,Menu::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        //solo logueo
                        val intent= Intent(this,Menu::class.java)
                        startActivity(intent)
                        finish()
                    }
                }else{
                    //si no existe un mensaje error
                    Toast.makeText(this, "usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }

            }
        }
        btnRegister.setOnClickListener {
            val userName=itUserNameRegister.text.toString()
            val password=itPassworRegister.text.toString()

            val newUser = User(userName,password)
            AppDataBase.getDataBase(applicationContext).userDao().insert(newUser)
            if(verificarUsuario(userName,password)){
                Toast.makeText(this, "usuario registrado con exito", Toast.LENGTH_SHORT).show()
                itUserNameRegister.text.clear()
                itPassworRegister.text.clear()
            }
        }
    }

    private fun verificarUsuario(user:String,password:String):Boolean {

        val listaUser :List<User> = AppDataBase.getDataBase(applicationContext).userDao().getAll()

        return listaUser.any{ it.userName==user && it.password==password}
        //esta funcion tendria que devolver si existe o no el usuario en db nada mas
    }
    private fun createChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel= NotificationChannel(
                MY_CHANNEL_ID,
                "my superChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description="descripcion del canal"
            }
            val notificationManager: NotificationManager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)

        }
    }
    private fun createSimpleNotification() {

        val intent=Intent(this,MainActivity::class.java).apply {
            //evitamos muchas instancias de la misma app en background..
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
        //creamos el builder
        val builder = NotificationCompat.Builder(this, MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle("Recordar activado")
            .setContentText("Tu usuario y contraseña seran recordados. La proxima se inicara la app automaticamente :)")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        //verificamos si tenemos lso permisos y si no los tenemos los pedimos
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //solicitar el permiso si no está otorgado
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
            return
        }

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }

}
