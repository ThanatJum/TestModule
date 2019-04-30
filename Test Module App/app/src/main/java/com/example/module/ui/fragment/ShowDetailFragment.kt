package com.example.module.ui.fragment


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.module.R
import com.example.module.database.room.entity.ProductEntity
import com.example.module.ui.recycleView.adapter.ProductAdapter
import com.example.module.utility.Resource
import com.example.module.utility.Status
import com.example.module.viewModel.ProductViewModel
import javax.inject.Inject


class ShowDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var productViewModel: ProductViewModel
    lateinit var myProductListAdapter : ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_detail, container, false)
    }

    private fun initialize(){
        productViewModel = ViewModelProviders.of(this,viewModelFactory).get(ProductViewModel::class.java)
        productViewModel.onProductTriggerList.observe(this,myProductViewModelObserve)
        myProductListAdapter = ProductAdapter()

    }
    private val myProductViewModelObserve = Observer<Resource<ProductEntity>>{productEntity ->
    when {
        productEntity.status == Status.LOADING -> {

        }
        productEntity.status == Status.SUCCESS ->{

        }
    }
    }


}
