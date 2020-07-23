package com.reschikov.testtask65apps.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reschikov.testtask65apps.ui.base.ViewModelFactory
import com.reschikov.testtask65apps.ui.employeecard.EmployeeCardViewModel
import com.reschikov.testtask65apps.ui.employees.EmployeesViewModel
import com.reschikov.testtask65apps.ui.specialities.SpecialtiesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SpecialtiesViewModel::class)
    fun bindSpecialtiesViewModel(viewModel: SpecialtiesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeesViewModel::class)
    fun bindEmployeesViewModel(viewModel: EmployeesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeCardViewModel::class)
    fun bindEmployeeCardsViewModel(viewModel: EmployeeCardViewModel) : ViewModel

    @Singleton
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}