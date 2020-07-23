package com.reschikov.testtask65apps.cache

import androidx.room.*
import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.entity.dot.EmployeeCard

@Dao
interface AppDataBaseDao {

    @Query("SELECT * FROM speciality")
    fun getListOfSpecialties() : List<Speciality>

    @Query("SELECT max(id) FROM employee")
    fun getLastId() : Long?

    @Transaction
    @Query("SELECT employee.* FROM employee INNER JOIN employees_specialities ON employee_id = employee.id WHERE specialization_id = :specialityCode")
    fun getListOfEmployees(specialityCode : Int) : List<Employee>

    @Transaction
    @Query("SELECT * FROM employee WHERE employee.id = :employeeId")
    fun getEmployeeCard(employeeId : Long) : EmployeeCard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSpecialtyData(specialties : List<Speciality>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEmployeeData(employees : List<Employee>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEmployeesSpecialties(list : List<EmployeesSpecialities>)
}