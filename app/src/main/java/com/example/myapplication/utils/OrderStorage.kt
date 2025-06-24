package com.example.myapplication.utils

import android.content.Context
import com.google.gson.Gson
import com.example.myapplication.data.Entities.Order
import java.io.File

object OrderStorage {

    private const val FILE_NAME = "orders.json"

    fun saveOrder(context: Context, order: Order) {
        val gson = Gson()
        val file = File(context.filesDir, FILE_NAME)

        val ordersList: MutableList<Order> = if (file.exists()) {
            val existingJson = file.readText()
            if (existingJson.isNotEmpty()) {
                gson.fromJson(existingJson, Array<Order>::class.java).toMutableList()
            } else {
                mutableListOf()
            }
        } else {
            mutableListOf()
        }

        ordersList.add(order)
        file.writeText(gson.toJson(ordersList))
    }

    fun loadOrders(context: Context): List<Order> {
        val gson = Gson()
        val file = File(context.filesDir, FILE_NAME)
        return if (file.exists()) {
            val json = file.readText()
            if (json.isNotEmpty()) {
                gson.fromJson(json, Array<Order>::class.java).toList()
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    }
}
