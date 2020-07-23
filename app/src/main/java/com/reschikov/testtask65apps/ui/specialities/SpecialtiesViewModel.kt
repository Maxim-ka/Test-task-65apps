package com.reschikov.testtask65apps.ui.specialities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.entity.dot.NoNetWork
import com.reschikov.testtask65apps.net.CheckNetWork
import com.reschikov.testtask65apps.repository.Derivable
import com.reschikov.testtask65apps.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class SpecialtiesViewModel @Inject constructor(derivable: Derivable?,
                                               private val stateNetwork : Provider<CheckNetWork>)
    : BaseViewModel(derivable) {

    private val dataListSpecialities = MutableLiveData<List<Speciality>>()
    private val observerNetWork by lazy {
        Observer<Boolean>{ if (it && networkMonitoring) loadData() }
    }
    private var networkMonitoring = false

    init {
        loadData()
    }

    fun getLivDataListSpeciality(): LiveData<List<Speciality>> = dataListSpecialities

    private fun loadData(){
        isVisibleProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            derivable?.getListSpecialities()?.let {
                it.first?.let { list ->
                    dataListSpecialities.postValue(list)
                    finishNetworkMonitoring()
                }
                if (it.second is NoNetWork) monitorNetworkStatus()
                error.postValue(it.second)
                isVisibleProgress.postValue(false)
            }
        }
    }

    private fun monitorNetworkStatus(){
        viewModelScope.launch(Dispatchers.Main) {
            networkMonitoring = true
            stateNetwork.get().observeForever(observerNetWork)
        }
    }

    private fun finishNetworkMonitoring(){
        viewModelScope.launch(Dispatchers.Main) {
            if (networkMonitoring) {
                networkMonitoring = false
                stateNetwork.get().removeObserver(observerNetWork)
            }
        }
    }

    override fun onCleared() {
        finishNetworkMonitoring()
        super.onCleared()
    }
}