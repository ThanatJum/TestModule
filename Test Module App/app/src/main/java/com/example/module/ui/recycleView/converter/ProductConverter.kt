package com.example.module.ui.recycleView.converter

import android.content.Context
import com.example.module.database.room.responseFromAPI.ProductItem
import com.example.module.ui.recycleView.base.BaseProduct
import com.example.module.ui.recycleView.holder.ProductHeaderHolder
import com.example.module.ui.recycleView.holder.ProductHolder
import com.example.module.ui.recycleView.holder.ProductSum
import com.example.module.ui.recycleView.item.ProductHeaderItem
import com.example.module.ui.recycleView.item.ProductHolderItem
import com.example.module.ui.recycleView.item.ProductSumItem

class ProductConverter {

fun createBaseProductList(productList: ArrayList<ProductItem>? ) : ArrayList<BaseProduct>{
    val baseProductList = ArrayList<BaseProduct>()
    baseProductList.add(createProductHeader())
    if (productList != null) {
        for(evenItem in productList)
            baseProductList.add(createProductHolder(evenItem))
    }
    baseProductList.add(createProductSum(1))
    return baseProductList
}



    private fun createProductHeader() : ProductHeaderItem{
        return ProductHeaderItem()
    }
    private fun createProductHolder(productList: ProductItem) : ProductHolderItem{
        return ProductHolderItem(productList)
    }
    private fun createProductSum(totalP:Int?) : ProductSumItem{
        return ProductSumItem(totalP)
    }
}