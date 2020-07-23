package com.reschikov.testtask65apps.repository

import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.net.model.Response

interface Transformable {

    fun convertToTables(responses: List<Response>, lastEmployeeId : Long) :
            Triple<List<Speciality>, List<Employee>, List<EmployeesSpecialities>>
}