package com.example.myapplication.viewmodel

import com.example.myapplication.data.Entities.Product

sealed class ProductState {
    object Loading : ProductState()
    data class Success(val products: List<Product>) : ProductState()
    data class Error(val message: String) : ProductState()
}