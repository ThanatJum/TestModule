package com.example.module.repository.utility

import android.arch.lifecycle.LiveData
import com.example.module.api.ApiResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoryLiveData<T>(private val deferred: Deferred<Response<T>>) : LiveData<ApiResponse<T>>() {
    fun execute() {
        GlobalScope.launch {
            try {
                val result: Response<T> = deferred.await()
                postValue(ApiResponse.create(result))
            } catch (e: Exception) {
                postValue(ApiResponse.create(e))
            }
        }
    }
}