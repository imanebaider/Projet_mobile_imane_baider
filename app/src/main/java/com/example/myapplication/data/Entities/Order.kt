package com.example.myapplication.data.Entities

data class OrderItem(
    val productId: String,
    val quantity: Int
)

data class Order(
    val items: List<OrderItem>,
    val timestamp: Long = System.currentTimeMillis()
)
