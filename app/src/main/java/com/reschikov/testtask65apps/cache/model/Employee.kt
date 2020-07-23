package com.reschikov.testtask65apps.cache.model

import androidx.room.*

@Entity(tableName = "employee")
data class Employee(@PrimaryKey var id: Long,
                    @ColumnInfo(name = "first_name") val fName : String,
                    @ColumnInfo(name = "last_name") val lName : String,
                    val birthday : Long?){

    @Ignore var age : Int = 0
}