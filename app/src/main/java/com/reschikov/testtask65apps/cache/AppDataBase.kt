package com.reschikov.testtask65apps.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality

@Database(entities = [Speciality::class, Employee::class, EmployeesSpecialities::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun dataBaseDao() : AppDataBaseDao
}