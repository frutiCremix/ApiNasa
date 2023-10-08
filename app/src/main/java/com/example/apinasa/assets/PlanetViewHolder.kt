package com.example.apinasa.assets

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.apinasa.Planet
import com.example.apinasa.R

class PlanetViewHolder(view : View): ViewHolder(view) {

    //recuperamos elementos del layout
    private val planetName= view.findViewById<TextView>(R.id.tvPlanetName)
    private val imagePlanet=view.findViewById<ImageView>(R.id.ivPlanet)
    private val planetDescription=view.findViewById<TextView>(R.id.tvDescripcion)

    fun render(planetModel: Planet){
        //cambiamos los valores en el layout
        planetName.text=planetModel.nombre
        planetDescription.text=planetModel.descripcion

        // Cargar la imagen desde 'drawable' basada en el nombre del planeta
        val context = itemView.context
        val resourceId = context.resources.getIdentifier(
            planetModel.nombre.lowercase(), "drawable", context.packageName
        )

        if (resourceId != 0) {
            // Si se encuentra un recurso con el nombre del planeta, establece la imagen
            imagePlanet.setImageResource(resourceId)
        } else {
            // Si no se encuentra un recurso con ese nombre, puedes manejarlo de alguna manera
            // aquí, como establecer una imagen predeterminada o mostrar un mensaje de error.
        }

    }
    }
