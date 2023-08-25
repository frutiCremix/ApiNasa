package com.example.apinasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apinasa.assets.PlanetAdapter

class ListaPlanetasView : AppCompatActivity() {

    lateinit var btnBacktoMenu:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_planetas_view)
        init()


    }
    fun init(){
        btnBacktoMenu=findViewById<Button>(R.id.btnBacktoMenu)

        btnBacktoMenu.setOnClickListener {
            val intent=Intent(this,Menu::class.java)
            startActivity(intent)
            finish()
        }



        initRecyclerView()
    }
    private fun initRecyclerView() {
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerPlanet)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=PlanetAdapter(PlanetProvider.planetList)

    }
}