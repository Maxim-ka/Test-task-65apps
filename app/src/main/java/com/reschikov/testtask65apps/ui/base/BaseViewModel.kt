package com.reschikov.testtask65apps.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reschikov.testtask65apps.repository.Derivable

abstract class BaseViewModel (protected var derivable: Derivable?) : ViewModel() {

    protected val error = MutableLiveData<Throwable?>()
    protected val isVisibleProgress = MutableLiveData<Boolean>()

    fun getErrorMsg() : LiveData<Throwable?> = error
    fun isVisibleProgress() : LiveData<Boolean> = isVisibleProgress

    override fun onCleared() {
        super.onCleared()
        derivable = null
    }
}