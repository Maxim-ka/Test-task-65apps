package com.reschikov.testtask65apps.entity.dot

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.EmployeesSpecialities
import com.reschikov.testtask65apps.cache.model.Speciality

data class EmployeeCard (@Embedded val employee: Employee,
                         @Relation(parentColumn = "id",
                             entityColumn = "id",
                             entity = Speciality::class,
                             associateBy = Junction(
                                 value = EmployeesSpecialities::class,
                                 parentColumn = "employee_id",
                                 entityColumn = "specialization_id"
                             ),
                             projection = ["name"]
                         ) val specialityName : List<String>
)