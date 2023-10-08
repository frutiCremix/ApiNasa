package com.example.apinasa.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apinasa.R

class ApiNasaAdapter (private val images:List<String>): RecyclerView.Adapter<ApiNasaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiNasaViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ApiNasaViewHolder(layoutInflater.inflate(R.layout.item_api_nasa_response,parent,false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ApiNasaViewHolder, position: Int) {
        val item=images[position]
        holder.bind(item)
    }


}