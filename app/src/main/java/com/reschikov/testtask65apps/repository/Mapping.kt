package com.reschikov.testtask65apps.repository

import com.reschikov.testtask65apps.DASH
import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.net.model.Response
import com.reschikov.testtask65apps.net.model.Specialty
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val INITIAL = 0
private const val NUMBER_of_CHARACTERS_PER_DAY = 2
private const val REVERSE_PATTERN = "yyyy-MM-dd"
private const val DIRECT_PATTERN = "dd-MM-yyyy"

class Mapping @Inject constructor(private val simpleDateFormat: SimpleDateFormat) :
    Transformable {

    override fun convertToTables(responses: List<Response>, lastEmployeeId: Long) :
            Triple<List<Speciality>, List<Employee>, List<EmployeesSpecialities>> {
        var employeeId = lastEmployeeId
        val employees = mutableListOf<Employee>()
        val employeesSpecialities = mutableListOf<EmployeesSpecialities>()
        val setSpeciality = mutableSetOf<Speciality>()
        responses.forEach {response ->
            val employee = createEmployee(response, ++employeeId)
            response.specialty.forEach{
                employeesSpecialities.add(createEmployeesSpecialities(it, employee.id))
                setSpeciality.add(createSpecialization(it))
            }
            employees.add(employee)
        }
        return Triple(setSpeciality.toList(), employees, employeesSpecialities)
    }

    private fun createEmployee(response: Response, employeeId : Long) : Employee {
        return response.run {
            Employee(
                id = employeeId,
                fName = checkNamesEmployee(fName),
                lName = checkNamesEmployee(lName),
                birthday = checkBirthDay(birthday)
            )
        }
    }

    private fun checkNamesEmployee(name : String) : String {
        return name
            .toLowerCase(Locale.ROOT)
            .replaceFirst(name[INITIAL], name[INITIAL].toUpperCase(), true)
    }

    private fun checkBirthDay(string: String?) : Long?{
        if (string.isNullOrEmpty() || string.isNullOrBlank()) return null
        return changeFormatDate(string)
    }

    private fun changeFormatDate(date : String) : Long? {
        if (date.substring(INITIAL, date.indexOf(DASH)).length > NUMBER_of_CHARACTERS_PER_DAY){
            simpleDateFormat.applyPattern(REVERSE_PATTERN)
        } else simpleDateFormat.applyPattern(DIRECT_PATTERN)
        val day = simpleDateFormat.parse(date) ?: return null
        return day.time
    }

    private fun createEmployeesSpecialities(specialty: Specialty, employeeId : Long) : EmployeesSpecialities {
        return EmployeesSpecialities(specializationId = specialty.specialtyId, employeeId = employeeId)
    }

    private fun createSpecialization(specialty : Specialty) : Speciality {
        return specialty.run {
            Speciality(id = specialtyId, name = name)
        }
    }
}