package com.reschikov.testtask65apps.cache

import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.cache.model.EmployeeCard
import com.reschikov.testtask65apps.repository.Cached
import com.reschikov.testtask65apps.usecase.Calculated
import javax.inject.Inject

class CacheProvider @Inject constructor(
                    private val appDataBaseDao: AppDataBaseDao,
                    private val calculated: Calculated
                    ) : Cached {

    override suspend fun getAllSpecialties(): List<Speciality> = appDataBaseDao.getListOfSpecialties()

    override suspend fun getWorkersByProfession(specialityCode: Int): List<Employee> {
        val list = appDataBaseDao.getListOfEmployees(specialityCode)
        list.map { employee -> employee.age = calculated.calculateAge(employee.birthday) }
        return list
    }

    override suspend fun getEmployeeCard(employeeId: Long) : EmployeeCard {
        val card = appDataBaseDao.getEmployeeCard(employeeId)
        card.employee.age = calculated.calculateAge(card.employee.birthday)
        return card
    }

    override suspend fun getLastIdEmployee(): Long {
        return appDataBaseDao.getLastId() ?: 0
    }

    override suspend fun cacheSpecialties(specialities: List<Speciality>) {
        appDataBaseDao.saveSpecialtyData(specialities)
    }

    override suspend fun cacheEmployees(employees: List<Employee>) {
        appDataBaseDao.saveEmployeeData(employees)
    }

    override suspend fun cacheEmployeesSpecialities(employees: List<EmployeesSpecialities>) {
        appDataBaseDao.saveEmployeesSpecialties(employees)
    }
}