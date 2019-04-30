package com.example.module.api.utility

import android.arch.lifecycle.MutableLiveData

class Trigger<T> {
    private val livedata: MutableLiveData<Data<T>> = MutableLiveData()
    private fun pull(value: T, needFresh: Boolean) {
        livedata.value = Data(value, needFresh)
    }

    fun pull(value: T) {
        pull(value, false)
    }

    fun pullFresh(value: T) {
        pull(value, true)
    }

    fun getLiveData() = livedata

    data class Data<T>(var value: T, var needFresh: Boolean)
}