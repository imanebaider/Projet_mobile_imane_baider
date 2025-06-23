package com.example.myapplication.data.Entities

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productID")
    val id: String,

    @SerializedName("productName")
    val name: String,

    @SerializedName("productPrice")
    val price: Double,

    // لاحظ أن imageResId فالعادة ما كيتكونش ف API، غالبا كيكون رابط صورة
    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("productDescription")
    val description: String,

    @SerializedName("productRating")
    val rating: Float = 0f,

    @SerializedName("productBrand")
    val brand: String = "",

    @SerializedName("productDiscount")
    val discount: Int = 0,

    @SerializedName("productReviews")
    val reviews: Double
)
