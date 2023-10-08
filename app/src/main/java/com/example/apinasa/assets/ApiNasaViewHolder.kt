package com.example.apinasa.assets

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.apinasa.R
import com.squareup.picasso.Picasso

class ApiNasaViewHolder (view : View): RecyclerView.ViewHolder(view) {
    private val imagen: ImageView = view.findViewById(R.id.ivDog)
    fun bind(image:String){
        Picasso.get().load(image).into(imagen)
    }
}