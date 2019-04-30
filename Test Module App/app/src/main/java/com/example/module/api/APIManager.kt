package com.example.module.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIManager() {

    private  fun apiManager():Retrofit{
        return Retrofit.Builder()
            .baseUrl("mock.nextzy.me/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                .create())).build()
    }
    fun getProductList():ProductApi{
        return apiManager().create(ProductApi::class.java)
    }
}