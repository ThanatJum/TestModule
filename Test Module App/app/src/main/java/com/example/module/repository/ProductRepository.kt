package com.example.module.repository

import android.arch.lifecycle.LiveData
import com.example.module.api.APIManager
import com.example.module.api.utility.TriggerEmpty
import com.example.module.database.room.dao.ProductDao
import com.example.module.database.room.entity.ProductEntity
import com.example.module.database.room.responseFromAPI.ProductResponse
import com.example.module.repository.utility.NetworkBoundResource
import com.example.module.utility.AppExecutors
import com.example.module.utility.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Response
import javax.inject.Inject


class ProductRepository @Inject constructor (private val productDao: ProductDao,
                                             private val appExecutors: AppExecutors,
                                             private val apiManager: APIManager) {
    fun getProductList(request: TriggerEmpty.Data): LiveData<Resource<ProductEntity>> {
        return object : NetworkBoundResource<ProductEntity,ProductResponse>(appExecutors){



            override fun convertToResultType(response: ProductResponse): ProductEntity {
                return ProductEntity(
                    productList = response.productListApi
                )

            }

            override fun createCall(): Deferred<Response<ProductResponse>> = GlobalScope.async {
                apiManager.getProductList().getAllProduct().execute()
            }

            override fun saveCallResult(item: ProductEntity) {
                productDao.insertProduct(item)
            }

            override fun loadFromDb(): LiveData<ProductEntity> {
                return productDao.getAlldata()
            }
            override fun shouldFetch(data: ProductEntity?): Boolean {
                return data == null &&
                        request.needFresh
            }

            override fun onFetchFailed(errorMessage: String) {
                //  Do nothing
            }

        }.asLiveData()
    }


}