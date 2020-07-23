package com.reschikov.testtask65apps.ui.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.repository.Derivable
import com.reschikov.testtask65apps.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeesViewModel @Inject constructor(derivable: Derivable?) : BaseViewModel(derivable) {

    private val dataListEmployee = MutableLiveData<List<Employee>>()

    fun getDataListEmployeeCards() : LiveData<List<Employee>> = dataListEmployee

    fun getListEmployeeCards(code :Int){
        isVisibleProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            derivable?.getListEmployee(code)?.let {
                it.first?.let {list -> dataListEmployee.postValue(list) }
                error.postValue(it.second)
                isVisibleProgress.postValue(false)
            }
        }
    }

}