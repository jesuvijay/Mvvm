package com.jesu.dagger_hilt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModels::class.java)){
            return MainViewModels() as T
        }
        throw IllegalArgumentException("unknown viewmodel")
    }


}