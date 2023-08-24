package com.example.apinasa.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apinasa.Planet
import com.example.apinasa.R

class PlanetAdapter(private val planetList:List<Planet>):RecyclerView.Adapter<PlanetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return PlanetViewHolder(inflater.inflate(R.layout.item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return planetList.size
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val item=planetList[position]
        holder.render(item)
    }
}