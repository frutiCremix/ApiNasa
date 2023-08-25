package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var btnSistemaSolar:Button
class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
    }

    fun init(){
        btnSistemaSolar=findViewById<Button>(R.id.btnSistemaSolar)

        btnSistemaSolar.setOnClickListener {
            val intent=Intent(this,ListaPlanetasView::class.java)
            startActivity(intent)
        }
    }
}