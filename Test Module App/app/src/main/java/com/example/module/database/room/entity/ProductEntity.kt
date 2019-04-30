package com.example.module.database.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.module.database.room.responseFromAPI.ProductItem

@Entity(tableName = "product_table")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "data")
    var productList: ArrayList<ProductItem>?)




