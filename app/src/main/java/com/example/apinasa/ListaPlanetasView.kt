package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apinasa.assets.PlanetAdapter

class ListaPlanetasView : AppCompatActivity() {


    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_planetas_view)
        init()


    }
    private fun init(){


        initTooblarMenu()

        initRecyclerView()
    }
    private fun initRecyclerView() {
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerPlanet)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=PlanetAdapter(PlanetProvider.planetList)

    }
    private fun initTooblarMenu(){
        toolbar=findViewById(R.id.toolbar)
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