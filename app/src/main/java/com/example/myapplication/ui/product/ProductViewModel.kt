package com.example.myapplication.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Entities.Product
import com.example.myapplication.data.Repository.ProductRepository
import com.example.myapplication.viewmodel.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _state = MutableStateFlow<ProductState>(ProductState.Loading)
    val state: StateFlow<ProductState> = _state

    init {
        loadInitialProducts()
    }

    private fun loadInitialProducts() {
        viewModelScope.launch {
            loadProducts()
        }
    }

    private suspend fun loadProducts() {
        _state.value = ProductState.Loading
        try {
            val products = repository.getProducts()
            _state.value = ProductState.Success(products)
        } catch (e: Exception) {
            _state.value = ProductState.Error(e.message ?: "Error fetching products")
        }
    }

    fun getProduct(productId: String): Product? {
        val currentState = state.value
        return if (currentState is ProductState.Success) {
            currentState.products.find { it.id == productId }
        } else {
            null
        }
    }

    fun handleIntent(intent: ProductIntent) {
        when (intent) {
            is ProductIntent.LoadProducts -> {
                viewModelScope.launch {
                    loadProducts()
                }
            }
        }
    }
}
