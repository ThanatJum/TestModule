package com.example.module.ui.recycleView.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.module.R
import com.example.module.database.room.responseFromAPI.ProductItem
import com.example.module.ui.recycleView.base.BaseProduct
import com.example.module.ui.recycleView.holder.ProductHeaderHolder
import com.example.module.ui.recycleView.holder.ProductHolder
import com.example.module.ui.recycleView.holder.ProductSum
import com.example.module.ui.recycleView.item.ProductHeaderItem
import com.example.module.ui.recycleView.item.ProductHolderItem
import com.example.module.ui.recycleView.item.ProductSumItem
import com.example.module.ui.recycleView.type.ProductType

class ProductAdapter(private val productList: Array<BaseProduct>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ProductType.TYPE_HEADER -> ProductHeaderHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recycle_product_header_holder, parent, false)
            )
            ProductType.TYPE_PRODUCT -> ProductHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recycle_product_holder, parent, false)
            )
            ProductType.TYPE_SUM -> ProductSum(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recycle_product_sum_holder, parent, false)
            )
            else -> ProductHeaderHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recycle_product_header_holder, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductHeaderHolder -> setupProductHeader(holder, productList[position] as ProductHeaderItem)
            is ProductHolder -> setupProductHolder(holder, productList[position] as ProductHolderItem)
            is ProductSum -> setupProductSum(holder, productList[position] as ProductSumItem)
        }
    }
    private fun setupProductHeader(holder: ProductHeaderHolder,productHeaderItem: ProductHeaderItem){

    }
    private fun setupProductHolder(holder: ProductHolder,productHolderItem: ProductHolderItem){
        holder.nameProductList.text = productHolderItem.productList.name
        holder.priceProductList.text = productHolderItem.productList.price

    }
    private fun setupProductSum(holder: ProductSum,productSumItem: ProductSumItem){
        holder.productTextSum.setText(productSumItem.totalSum!!)

    }
}