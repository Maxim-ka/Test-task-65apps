package com.reschikov.testtask65apps.repository

import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.cache.model.EmployeeCard

interface Cached {

    suspend fun getAllSpecialties() : List<Speciality>
    suspend fun getWorkersByProfession(specialityCode : Int) : List<Employee>
    suspend fun getEmployeeCard(employeeId : Long) : EmployeeCard
    suspend fun getLastIdEmployee() : Long
    suspend fun cacheSpecialties(specialities: List<Speciality>)
    suspend fun cacheEmployees(employees: List<Employee>)
    suspend fun cacheEmployeesSpecialities(employees: List<EmployeesSpecialities>)
}