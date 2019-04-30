package com.example.module.ui.recycleView.holder

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_recycle_product_sum_holder.view.*

class ProductSum(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val productTextSum : TextView = itemView.textSum
}