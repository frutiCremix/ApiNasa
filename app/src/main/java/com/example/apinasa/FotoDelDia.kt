package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class FotoDelDia : AppCompatActivity() {

    lateinit var btnBacktoMenu: Button
    lateinit var toolbar: Toolbar;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto_del_dia)

        initTooblarMenu()
        btnBacktoMenu=findViewById<Button>(R.id.btnBacktoMenu)

        btnBacktoMenu.setOnClickListener {
            val intent= Intent(this,Menu::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun initTooblarMenu(){
        toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title=resources.getString(R.string.app_name)
    }
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.item_agregar){
            val intent= Intent(this,Menu::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
