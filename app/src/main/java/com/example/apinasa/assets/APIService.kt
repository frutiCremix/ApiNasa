package com.example.apinasa.assets


import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//const key = "1aCbJgSXsa29PPBBQU0V7zxRSxadM1KuVaS4Pb6Z";
//`https://api.nasa.gov/planetary/apod?api_key=${key}`
interface APIService {

    //colocar la parte relativa de la url aca
    @GET("search")
    fun searchNasaImages(@Query("q") query: String): Call<ApiNasaResponse>
    @GET("planetary/apod?api_key=1aCbJgSXsa29PPBBQU0V7zxRSxadM1KuVaS4Pb6Z")
    suspend fun searchDayImage() : Response<ApiNasaDayImageResponse>

    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov/"
        private const val BASE_URL_2="https://api.nasa.gov/"


        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(APIService::class.java)
        }
        fun createDayImage(): APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_2)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(APIService::class.java)
        }

    }
}