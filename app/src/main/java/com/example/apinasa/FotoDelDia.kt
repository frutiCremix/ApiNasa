package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FotoDelDia : AppCompatActivity() {

    lateinit var btnBacktoMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto_del_dia)

        btnBacktoMenu=findViewById<Button>(R.id.btnBacktoMenu)

        btnBacktoMenu.setOnClickListener {
            val intent= Intent(this,Menu::class.java)
            startActivity(intent)
            finish()
        }
    }
}