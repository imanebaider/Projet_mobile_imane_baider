package com.example.myapplication

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageResId: Int,
    val description: String,
    val rating: Float = 0f,
    val brand: String = "",
    val discount: Int = 0,
    val reviews: Double
)
