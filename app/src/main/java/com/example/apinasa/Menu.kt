package com.example.apinasa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.widget.Toolbar


class Menu : AppCompatActivity() {
    private lateinit var btnSistemaSolar:Button
    private lateinit var btnFotoDelDia:Button
    private lateinit var btnFotoMarte:Button
    private lateinit var btnExit:Button
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
    }

    private fun init(){
        btnSistemaSolar=findViewById(R.id.btnSistemaSolar)
        btnFotoDelDia=findViewById(R.id.btnFotoDelDia)
        btnFotoMarte=findViewById(R.id.btnMarte)
        btnExit=findViewById(R.id.delete)
        toolbar=findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar!!.title=resources.getString(R.string.app_name)


        btnFotoDelDia.setOnClickListener {
            val intent=Intent(this,FotoDelDia::class.java)
            startActivity(intent)
            finish()
        }
        btnFotoMarte.setOnClickListener {
            val intent=Intent(this,FotoMarte::class.java)
            startActivity(intent)
            finish()
        }
        btnSistemaSolar.setOnClickListener {
            val intent=Intent(this,ListaPlanetasView::class.java)
            startActivity(intent)
            finish()
        }
        btnExit.setOnClickListener{
           val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Datos de SharedPreferences eliminados", Toast.LENGTH_SHORT).show()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}