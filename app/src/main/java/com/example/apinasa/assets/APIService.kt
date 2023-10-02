package com.example.apinasa.assets


import retrofit2.Call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    //colocar la parte relativa de la url aca
    @GET("search")
    fun searchNasaImages(@Query("q") query: String): Call<ApiNasaResponse>

    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov/"

        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}