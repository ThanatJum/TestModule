package com.example.module.database.room.responseFromAPI

import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @SerializedName("data")
    val productListApi: ArrayList<ProductItem>?
)