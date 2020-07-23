package com.reschikov.testtask65apps.di.adapter

import com.reschikov.testtask65apps.ui.employees.EmployeesAdapter
import com.reschikov.testtask65apps.ui.employees.EmployeesFragment
import com.reschikov.testtask65apps.ui.specialities.SpecialtiesAdapter
import com.reschikov.testtask65apps.ui.specialities.SpecialtiesFragment
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @ScopeFragment
    @Provides
    fun provideEmployeesAdapter(fragment: EmployeesFragment) : EmployeesAdapter{
        return EmployeesAdapter(fragment)
    }

    @ScopeFragment
    @Provides
    fun provideSpecialtiesAdapter(fragment: SpecialtiesFragment) : SpecialtiesAdapter{
        return SpecialtiesAdapter(fragment)
    }
}