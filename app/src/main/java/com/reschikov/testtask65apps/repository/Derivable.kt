package com.reschikov.testtask65apps.repository

import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.cache.model.EmployeeCard

interface Derivable {

    suspend fun getListSpecialities() : Pair<List<Speciality>?, Throwable?>
    suspend fun getListEmployee(speciality : Int) : Pair<List<Employee>?, Throwable?>
    suspend fun getEmployeeCard(employeeId : Long) : Pair<EmployeeCard?, Throwable?>
}