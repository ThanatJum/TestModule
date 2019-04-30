package com.example.module.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.module.api.utility.TriggerEmpty
import com.example.module.database.room.database.ProductDatabase
import com.example.module.database.room.entity.ProductEntity
import com.example.module.repository.ProductRepository
import com.example.module.utility.AbsentLivedata
import com.example.module.utility.Resource
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProductViewModel @Inject constructor(productRepository: ProductRepository) : ViewModel() {
    private  val onProductTrigger = TriggerEmpty()

    val onProductTriggerList: LiveData<Resource<ProductEntity>> = Transformations
        .switchMap(onProductTrigger.getLiveData()){ productListRequest ->
            if(productListRequest == null){
                AbsentLivedata.create()
            } else {
                productRepository.getProductList(productListRequest)
            }
        }
    fun  triggerProductList(){
        onProductTrigger.pullFresh()
    }

}