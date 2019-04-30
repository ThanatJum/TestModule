package com.example.module.utility

import android.arch.lifecycle.LiveData

class AbsentLivedata<T : Any?> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }
    companion object {
        fun <T> create(): LiveData<T>{
            return AbsentLivedata()
        }
    }
}