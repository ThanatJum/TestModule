package com.example.module.ui.recycleView.item

import android.content.Context
import com.example.module.database.room.responseFromAPI.ProductItem
import com.example.module.ui.recycleView.adapter.ProductAdapter
import com.example.module.ui.recycleView.base.BaseProduct
import com.example.module.ui.recycleView.type.ProductType

class ProductHolderItem(var productList: ProductItem)
    :BaseProduct(ProductType.TYPE_PRODUCT){
}