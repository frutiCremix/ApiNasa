package com.example.apinasa

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private val demora : Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val r = Runnable {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        Handler(Looper.getMainLooper()).postDelayed(r, demora)
    }
}