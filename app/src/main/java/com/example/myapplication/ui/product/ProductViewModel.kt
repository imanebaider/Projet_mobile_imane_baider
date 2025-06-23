package com.example.myapplication.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Entities.Product
import com.example.myapplication.data.Repository.ProductRepository
import com.example.myapplication.viewmodel.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Log



@HiltViewModel
class ProductViewModel @Inject constructor(

    private val repository: ProductRepository
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
    private var productsCache: List<Product> = emptyList()

    private suspend fun loadProducts() {
        _state.value = ProductState.Loading
        try {
            Log.d("products repo", "loadProducts")


            val products = repository.getProducts()
            productsCache = products
            _state.value = ProductState.Success(products)
        } catch (e: Exception) {
            Log.d("products repo", "Exception")


            _state.value = ProductState.Error(e.message ?: "Error fetching products")
        }
    }


    fun getProduct(productId: String): Product? {
        return productsCache.find { it.id == productId }
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
