package com.example.myapplication.data.Repository



import com.example.myapplication.data.Entities.Product
import com.example.myapplication.R
import kotlinx.coroutines.delay
import javax.inject.Inject
import com.example.myapplication.data.Api.ProductApi
import android.util.Log
import java.io.Console





class ProductRepository @Inject constructor(
    private val api: ProductApi
) {

    suspend fun getProducts(): List<Product> {
        // fetch data from a remote serverAdd commentMore actions
        val products = api.getProducts()
                Log.d("products repo", "size :"+ products.size)
        return products
    }
}