package com.example.module.database.room.responseFromAPI

import com.google.gson.annotations.SerializedName

data class ProductItem (
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: String? = null
)