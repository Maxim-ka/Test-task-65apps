package com.reschikov.testtask65apps.cache.model

import androidx.room.*

@Entity(tableName = "employees_specialities", foreignKeys = [
    ForeignKey(entity = Speciality::class, parentColumns = ["id"], childColumns = ["specialization_id"],
        onDelete = ForeignKey.CASCADE, deferred = true),
    ForeignKey(entity = Employee::class, parentColumns = ["id"], childColumns = ["employee_id"],
        onDelete = ForeignKey.CASCADE , deferred = true)],
    indices = [Index("employee_id"), Index( "specialization_id", "id")])
data class EmployeesSpecialities (@PrimaryKey(autoGenerate = true) var id: Long = 0L,
                                  @ColumnInfo(name = "specialization_id") val specializationId : Int,
                                  @ColumnInfo(name = "employee_id") val employeeId : Long)