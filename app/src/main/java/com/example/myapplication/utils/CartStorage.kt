package com.example.myapplication.utils


import android.content.Context
import com.example.myapplication.data.Entities.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object CartStorage {
    private const val FILE_NAME = "cart.json"

    fun loadCart(context: Context): MutableList<Product> {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return mutableListOf()

        val json = file.readText()
        val type = object : TypeToken<MutableList<Product>>() {}.type
        return Gson().fromJson(json, type) ?: mutableListOf()
    }

    fun saveCart(context: Context, cart: List<Product>) {
        val file = File(context.filesDir, FILE_NAME)
        val json = Gson().toJson(cart)
        file.writeText(json)
    }

    fun addToCart(context: Context, product: Product) {
        val cart = loadCart(context)
        cart.add(product)
        saveCart(context, cart)
    }
}
