package com.example.apinasa

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apinasa.assets.APIService
import com.example.apinasa.assets.ApiNasaAdapter
import com.example.apinasa.assets.ApiNasaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FotoNasa : AppCompatActivity(),SearchView.OnQueryTextListener {
    //declaramos variables
    private lateinit var rvApiNasaResponse: RecyclerView
    private lateinit var apiNasaResponseAdapter:ApiNasaAdapter
    private val listResponseImages= mutableListOf<String>()
    private lateinit var search: SearchView
    private lateinit var toolbar:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto_nasa)
        search=findViewById(R.id.svDogs)
        search.setOnQueryTextListener(this)
        toolbar=findViewById(R.id.toolbar)
        initTooblarMenu()
        initRecyclerView()
    }
    fun initRecyclerView(){
        apiNasaResponseAdapter=ApiNasaAdapter(listResponseImages)
        rvApiNasaResponse=findViewById(R.id.rvApiNasaResponse)
        rvApiNasaResponse.layoutManager= LinearLayoutManager(this)
        rvApiNasaResponse.adapter=apiNasaResponseAdapter
    }

    private fun searchByName(query: String) {
        // Creamos la instancia de la interfaz
        val api = APIService.create()

        // Realizamos la llamada a la API
        val call = api.searchNasaImages(query)

        // Utilizamos 'call.enqueue' para realizar la llamada de manera asíncrona
        call.enqueue(object : Callback<ApiNasaResponse> {
            override fun onResponse(
                call: Call<ApiNasaResponse>,
                response: Response<ApiNasaResponse>
            ) {
                try {
                    // Verificamos si la respuesta de la API fue exitosa
                    if (response.isSuccessful) {
                        val apiNasaResponse = response.body()
                        // Verificamos si el cuerpo de la respuesta no es nulo
                        if (apiNasaResponse != null) {
                            // Borramos la lista de imágenes previa en el RecyclerView
                            listResponseImages.clear()
                            // Verificamos si la respuesta contiene la estructura esperada
                            if (apiNasaResponse.collection != null) {
                                val items = apiNasaResponse.collection.items
                                // Verificamos si la lista de items no es nula
                                if (items != null) {
                                    // Filtramos las URLs de las imágenes
                                    val imageUrls = items.filter { item ->
                                        val links = item.links
                                        // Verificamos si hay enlaces y si alguno de ellos es una imagen
                                        links != null && links.any { link ->
                                            link.render == "image" && link.href.startsWith("https://images-assets.nasa.gov/image")
                                        }
                                    }.flatMap { item ->
                                        //ahora creamos la lista pero solo con las imagenes
                                        item.links?.filter { link ->
                                            link.render == "image" && link.href.startsWith("https://images-assets.nasa.gov/image")
                                        }?.mapNotNull { link -> link.href } ?: emptyList()
                                    }

                                    // Agregamos las URLs de las imágenes filtradas a la lista de imágenes
                                    listResponseImages.addAll(imageUrls)

                                    // Notificamos al adaptador del RecyclerView
                                    apiNasaResponseAdapter.notifyDataSetChanged()
                                } else {
                                    // Mostramos un error si la lista de items es nula
                                    showError("no se encontraron resultados")
                                }
                            } else {
                                // Mostramos un error si la estructura de la respuesta es incorrecta
                                showError("respuesta no disponible")
                            }
                        } else {
                            // Mostramos un error si el cuerpo de la respuesta es nulo
                            showError("no se encontraron resultados")
                        }
                    }
                } catch (e: NullPointerException) {
                    // Maneja la excepción aquí mostrando un mensaje de error
                    showErrorNull()
                }
            }

            override fun onFailure(call: Call<ApiNasaResponse>, t: Throwable) {
                // Maneja los errores de conexión aquí
                showError("error de conexion con la api")
            }
        })
    }
//por si hay una mala query
    private fun showErrorNull() {
        Toast.makeText(this,"No encontramos lo que buscas, intenta de nuevo", Toast.LENGTH_SHORT).show()
    }

//tengo varios errores que manejar por eso gestiono con esta funcion
    private fun showError(message :String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
//recuperamos la query del buscador
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }
//no hace nada pero habia que implementarla
    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    private fun initTooblarMenu(){
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title=resources.getString(R.string.buscador)
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


