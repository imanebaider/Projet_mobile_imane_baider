package com.example.myapplication.data.Entities

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("reviews")
    val reviews: Double
)
