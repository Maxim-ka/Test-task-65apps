package com.reschikov.testtask65apps.repository

import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.entity.dot.EmployeeCard
import com.reschikov.testtask65apps.net.model.Response
import dagger.Lazy
import javax.inject.Inject

class Repository @Inject constructor(
                 private val requested: Lazy<Requested>,
                 private val transformable: Lazy<Transformable>,
                 private val cached: Cached) : Derivable {

    override suspend fun getListSpecialities(): Pair<List<Speciality>?, Throwable?> {
        try {
            val specialities = cached.getAllSpecialties()
            if (specialities.isEmpty()) return getDataFromServer()
            return Pair(specialities, null)
        } catch (e: Exception) {
            return Pair(null, e)
        }
    }

    private suspend fun getDataFromServer() : Pair<List<Speciality>?, Throwable?> {
        try {
            val reply = requested.get().receive()
            if (reply.response.isEmpty()) return Pair(emptyList(), null)
            saveReply(reply.response)
            return Pair(cached.getAllSpecialties(), null)
        } catch (e: Throwable) {

            return Pair(null, e)
        }
    }

    private suspend fun saveReply(responses: List<Response>) {
        val lastId = cached.getLastIdEmployee()
        writeToCache(transformable.get().convertToTables(responses, lastId))
    }

    private suspend fun writeToCache(tables : Triple<List<Speciality>, List<Employee>, List<EmployeesSpecialities>>) {
        tables.run {
            if (first.isNotEmpty()) cached.cacheSpecialties(first)
            if (second.isNotEmpty()) cached.cacheEmployees(second)
            if (third.isNotEmpty()) cached.cacheEmployeesSpecialities(third)
        }
    }

    override suspend fun getListEmployee(speciality: Int): Pair<List<Employee>?, Throwable?> {
        return try {
            Pair(cached.getWorkersByProfession(speciality), null)
        } catch (e: Exception) {
            Pair(null, e)
        }
    }

    override suspend fun getEmployeeCard(employeeId: Long): Pair<EmployeeCard?, Throwable?> {
        return try {
            Pair(cached.getEmployeeCard(employeeId), null)
        } catch (e: Exception) {
            Pair(null, e)
        }
    }
}