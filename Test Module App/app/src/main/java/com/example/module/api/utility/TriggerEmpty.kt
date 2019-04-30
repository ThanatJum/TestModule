package com.example.module.api.utility

import android.arch.lifecycle.MutableLiveData

class TriggerEmpty {
    private val livedata: MutableLiveData<Data> = MutableLiveData()

    private fun pull(needFresh: Boolean) {
        livedata.value = Data(needFresh)
    }

    fun pull() {
        pull(false)
    }

    fun pullFresh() {
        pull(true)
    }

    fun getLiveData() = livedata

    data class Data(var needFresh: Boolean)
}