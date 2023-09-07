package com.example.apinasa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

lateinit var btnSistemaSolar:Button
lateinit var btnFotoDelDia:Button
lateinit var btnFotoMarte:Button
lateinit var btnExit:Button
class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
    }

    fun init(){
        btnSistemaSolar=findViewById<Button>(R.id.btnSistemaSolar)
        btnFotoDelDia=findViewById<Button>(R.id.btnFotoDelDia)
        btnFotoMarte=findViewById<Button>(R.id.btnMarte)
        btnExit=findViewById<Button>(R.id.delete)

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
        }
    }
}