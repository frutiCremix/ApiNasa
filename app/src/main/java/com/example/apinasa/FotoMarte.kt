package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.apinasa.R.*
lateinit var btn: Button
class FotoMarte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_foto_marte)

    btn=findViewById<Button>(R.id.btnBacktoMenu)
btn.setOnClickListener {
    val intent= Intent(this,Menu::class.java)
    startActivity(intent)
    finish()
}

    }
}