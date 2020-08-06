package com.reschikov.testtask65apps.di

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.reschikov.testtask65apps.cache.AppDataBase
import com.reschikov.testtask65apps.cache.AppDataBaseDao
import com.reschikov.testtask65apps.cache.CacheProvider
import com.reschikov.testtask65apps.di.adapter.AdapterModule
import com.reschikov.testtask65apps.di.adapter.ScopeFragment
import com.reschikov.testtask65apps.repository.Derivable
import com.reschikov.testtask65apps.repository.*
import com.reschikov.testtask65apps.ui.base.BaseFragment
import com.reschikov.testtask65apps.ui.employeecard.EmployeeCardFragment
import com.reschikov.testtask65apps.ui.employees.EmployeesFragment
import com.reschikov.testtask65apps.ui.specialities.SpecialtiesFragment
import com.reschikov.testtask65apps.usecase.Age
import com.reschikov.testtask65apps.usecase.Calculated
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import java.text.SimpleDateFormat
import javax.inject.Singleton

private const val NAME_DATABASE = "testtask65apps"

@Module
abstract class AppModule {

    companion object{

        @SuppressLint("JvmStaticProvidesInObjectDetector")
        @JvmStatic
        @Singleton
        @Provides
        fun provideAppDataBaseDao(context: Context) : AppDataBaseDao {
            return Room.databaseBuilder(context, AppDataBase::class.java, NAME_DATABASE)
                .build()
                .dataBaseDao()
        }

        @SuppressLint("SimpleDateFormat", "JvmStaticProvidesInObjectDetector")
        @JvmStatic
        @Provides
        fun provideSimpleDateFormat() : SimpleDateFormat = SimpleDateFormat()
    }

    @Binds
    abstract fun bindTransformable(mapping: Mapping) : Transformable

    @Singleton
    @Binds
    abstract fun bindCalculated(age: Age) : Calculated

    @Binds
    @Singleton
    abstract fun bindCached(cacheProvider: CacheProvider) : Cached

    @Binds
    @Singleton
    abstract fun bindDerivable(repository: Repository) : Derivable

    @ContributesAndroidInjector
    abstract fun employeeCardInjector () : EmployeeCardFragment

    @ScopeFragment
    @ContributesAndroidInjector(modules = [AdapterModule::class])
    abstract fun specialtiesInjector () : SpecialtiesFragment

    @ScopeFragment
    @ContributesAndroidInjector(modules = [AdapterModule::class])
    abstract fun employeesInjector () : EmployeesFragment

    @ContributesAndroidInjector
    abstract fun baseFragmentInjector () : BaseFragment
}