package com.example.module.api

import com.example.module.database.room.responseFromAPI.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {
    @GET("ProductList/v1.0/AllProduct")
    fun getAllProduct(): Call<ProductResponse>
}