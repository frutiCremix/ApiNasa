package com.example.apinasa

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.apinasa.assets.APIService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FotoDelDia : AppCompatActivity(), LifecycleOwner {

    private lateinit var toolbar: Toolbar
    private lateinit var imageContent:ImageView
    private lateinit var explanation:TextView
    private lateinit var copyright:TextView
    private lateinit var date:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto_del_dia)

        initTooblarMenu()
        init()
    }

    private fun init(){
        //recuperamos elementos de la UI
        imageContent = findViewById(R.id.imgContent)
        explanation = findViewById(R.id.explanation)
        copyright = findViewById(R.id.copyright)
        date = findViewById(R.id.date)

    lifecycleScope.launch(Dispatchers.IO){
        val api=APIService.createDayImage()

        try {

            val response = api.searchDayImage()

            if (response.isSuccessful) {
                val dayImageResponse = response.body()

                // Verifica si dayImageResponse no es nulo
                if (dayImageResponse != null) {

                    //llenamos la vista en el hilo principal
                    //no es necesario el runOnUiThread lifecycleScope te permite cambiar directamente la interfaz de usuario desde el hilo principal
                    //preguntar si cambiar por  withContext(Dispatchers.Main) {...}

                    runOnUiThread {
                        Picasso.get().load(dayImageResponse.url).into(imageContent)
                       explanation.text = dayImageResponse.explanation
                        copyright.text = dayImageResponse.copyright
                       date.text = dayImageResponse.date
                    }
                } else {
                    // Manejar el caso en que dayImageResponse sea nulo
                    showError("nulo")

                }
            } else {
                // Manejar el caso de respuesta no exitosa aquí
                showError("no hubo respuesta")
            }
        } catch (e:Exception){
            //manejar el error

            showError("error")
        }
    }

    }

    //tengo varios errores que manejar por eso gestiono con esta funcion
    private fun showError(message :String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun initTooblarMenu(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
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
