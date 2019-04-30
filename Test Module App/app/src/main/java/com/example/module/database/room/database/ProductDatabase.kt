package com.example.module.database.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.module.database.room.dao.ProductDao
import com.example.module.database.room.entity.ProductEntity

@Database(entities = [ProductEntity::class],version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDetail() : ProductDao

    companion object {
        var INSTANCE: ProductDatabase? = null
        fun getAppDataBase(context: Context): ProductDatabase? {
            if (INSTANCE == null){
                synchronized(ProductDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProductDatabase::class.java, "product_table").build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}