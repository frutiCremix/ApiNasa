package com.example.apinasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apinasa.assets.PlanetAdapter

class ListaPlanetasView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_planetas_view)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerPlanet)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=PlanetAdapter(PlanetProvider.planetList)

    }
}