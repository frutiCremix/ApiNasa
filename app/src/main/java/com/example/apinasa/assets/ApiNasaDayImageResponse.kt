package com.example.apinasa.assets

import com.google.gson.annotations.SerializedName

data class ApiNasaDayImageResponse(
    @SerializedName("title") val title:String,
    @SerializedName("url")val url :String,
    @SerializedName("hdurl")val hdurl :String,
    @SerializedName("explanation")val explanation :String,
    @SerializedName("date")val date :String,
    @SerializedName("copyright")val copyright:String
)