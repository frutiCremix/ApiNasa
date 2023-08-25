package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    val userList = mutableListOf<User>()
    lateinit var itUsername: EditText
    lateinit var itPassword: EditText
    lateinit var itUserNameRegister:EditText
    lateinit var itPassworRegister:EditText
    lateinit var btnLogin: Button
    lateinit var btnRegister:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        ///// Inicializamos usuarios de prueba
        userList.add(User("admin", "admin"))
        ////

        // Asignar referencias de las vistas a las variables
        //login
        itUsername = findViewById<EditText>(R.id.itUserNameLogin)
        itPassword = findViewById<EditText>(R.id.itPasswordLogin)
        btnLogin = findViewById<Button>(R.id.btnLogin)
        //register
        itUserNameRegister=findViewById<EditText>(R.id.itUserNameRegister)
        itPassworRegister=findViewById<EditText>(R.id.itPasswordRegister)
        btnRegister=findViewById<Button>(R.id.btnRegister)


        btnLogin.setOnClickListener {
            if (verificarLogin()) {
                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"user or password incorrect",Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            val userName=itUserNameRegister.text.toString()
            val password=itPassworRegister.text.toString()

            // Verificar si el usuario ya existe en la lista
            if (usuarioExistente(userName)) {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_LONG).show()
            } else if (userName.isNotEmpty() && password.isNotEmpty()) {
                // Crear un nuevo usuario solo si no existe
                val newUser = User(userName, password)
                userList.add(newUser)
                itUserNameRegister.text=null
                itPassworRegister.text=null

                Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun usuarioExistente( userName : String): Boolean {
        return userList.any { it.userName == userName }
    }

    private fun verificarLogin(): Boolean {
        val username = itUsername.text.toString()
        val password = itPassword.text.toString()

        // Busca un usuario con el nombre de usuario y contraseña ingresados
        val usuario = userList.find { it.userName == username && it.password == password }

        return usuario != null
    }
}
