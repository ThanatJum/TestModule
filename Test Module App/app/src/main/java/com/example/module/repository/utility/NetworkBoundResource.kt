package com.example.module.repository.utility

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.module.api.ApiEmptyResponse
import com.example.module.api.ApiErrorResponse
import com.example.module.api.ApiSuccessResponse
import com.example.module.utility.AppExecutors
import com.example.module.utility.Resource
import kotlinx.coroutines.Deferred
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors){
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val deferred = createCall()
        val apiResponse = RepositoryLiveData(deferred)
        apiResponse.execute()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse<*> -> {
                    appExecutors.diskIO().execute {
                        val data: ResultType = convertToResultType(processResponse(response as ApiSuccessResponse<RequestType>))
                        saveCallResult(data)
                        appExecutors.mainThread().execute {
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            result.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse<*> -> {
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse<*> -> {
                    appExecutors.mainThread().execute {
                        onFetchFailed(response.errorMessage)
                        result.addSource(dbSource) { newData ->
                            setValue(Resource.error(response.errorMessage, newData))
                        }
                    }
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: ResultType)

    @WorkerThread
    protected abstract fun convertToResultType(response: RequestType): ResultType

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): Deferred<Response<RequestType>>

    @MainThread
    protected abstract fun onFetchFailed(errorMessage: String)

    //  @MainThread
    //  protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}