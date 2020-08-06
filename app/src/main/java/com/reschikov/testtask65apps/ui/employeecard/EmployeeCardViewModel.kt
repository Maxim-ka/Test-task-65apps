package com.reschikov.testtask65apps.ui.employeecard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.reschikov.testtask65apps.cache.model.EmployeeCard
import com.reschikov.testtask65apps.repository.Derivable
import com.reschikov.testtask65apps.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeCardViewModel @Inject constructor(derivable: Derivable?) : BaseViewModel (derivable) {

    private val employeeCardLiveData = MutableLiveData<EmployeeCard>()

    fun getEmployeeCardLiveData() : LiveData<EmployeeCard> = employeeCardLiveData

    fun getEmployeeCard(employeeId : Long) {
        isVisibleProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            derivable?.getEmployeeCard(employeeId)?.let {
                it.first?.let {card -> employeeCardLiveData.postValue(card) }
                error.postValue(it.second)
                isVisibleProgress.postValue(false)
            }
        }
    }
}