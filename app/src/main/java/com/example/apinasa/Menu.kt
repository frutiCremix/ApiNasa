package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var btnSistemaSolar:Button
lateinit var btnFotoDelDia:Button
lateinit var btnFotoMarte:Button
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
    }
}