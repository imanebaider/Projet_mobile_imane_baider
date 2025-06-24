package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.data.Entities.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FavoriteStorage {
    private const val PREF_NAME = "favorite_products"
    private const val KEY = "favorites"

    fun getFavorites(context: Context): MutableList<Product> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Product>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun isFavorite(context: Context, product: Product): Boolean {
        return getFavorites(context).any { it.id == product.id }
    }

    fun toggleFavorite(context: Context, product: Product) {
        val favorites = getFavorites(context)
        val exists = favorites.any { it.id == product.id }
        if (exists) {
            favorites.removeAll { it.id == product.id }
        } else {
            favorites.add(product)
        }
        saveFavorites(context, favorites)
    }

    private fun saveFavorites(context: Context, favorites: List<Product>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(favorites)
        prefs.edit().putString(KEY, json).apply()
    }
}
