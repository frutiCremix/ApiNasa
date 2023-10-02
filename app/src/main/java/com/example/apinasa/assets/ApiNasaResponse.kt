package com.example.apinasa.assets
import com.google.gson.annotations.SerializedName

data class ApiNasaResponse(
    @SerializedName("collection") val collection: Collection
)

data class Collection(
    @SerializedName("items") val items: List<Item>
)

data class Item(
    @SerializedName("links") val links: List<Link>,

)

data class Link(
    @SerializedName("href") val href: String,
    @SerializedName("rel") val rel: String,
    @SerializedName("render") val render: String
)

