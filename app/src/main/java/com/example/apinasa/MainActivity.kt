package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import android.content.Context
import android.content.SharedPreferences
import android.widget.CheckBox


class MainActivity : AppCompatActivity() {

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
                        Toast.makeText(this, "usuario recordado", Toast.LENGTH_SHORT).show()
                        //seteamos por defecto en el preference
                        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("userName",userName).apply()
                        sharedPreferences.edit().putString("password",password).apply()

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

        }
    }

    private fun verificarUsuario(user:String,password:String):Boolean {

        val listaUser :List<User> = AppDataBase.getDataBase(applicationContext).userDao().getAll()

        return listaUser.any{ it.userName==user && it.password==password}
        //esta funcion tendria que devolver si existe o no el usuario en db nada mas
    }


}
