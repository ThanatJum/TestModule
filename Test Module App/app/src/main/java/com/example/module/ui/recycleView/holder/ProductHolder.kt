package com.example.module.ui.recycleView.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_recycle_product_holder.view.*

class ProductHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val nameProductList: TextView = itemView.nameProduct
    val priceProductList: TextView = itemView.priceProduct
}