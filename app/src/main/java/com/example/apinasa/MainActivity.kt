package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class MainActivity : AppCompatActivity() {
    //es solo para dev borrar luego incluyendo el del layout
    lateinit var prueba: Button
/////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    /////////////////////////////////////////
        prueba=findViewById(R.id.prueba)
        prueba.setOnClickListener {
            val intent=Intent(this,ListaPlanetasView::class.java)
            startActivity(intent)
        }
    /////////////////////////////////////////////
    }

}