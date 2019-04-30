package com.example.module.database.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.module.database.room.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    fun getAlldata(): LiveData<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEdit:ProductEntity)

    @Query("DELETE FROM product_table")
    fun clearCategoryListFromDb()
}