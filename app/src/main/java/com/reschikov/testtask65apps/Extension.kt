package com.reschikov.testtask65apps

const val DASH = "-"
const val KEY_SPECIALTY_CODE = "key specialty id"
const val KEY_EMPLOYEE_CARD = "key employee card"

fun Int.getTextAge() : String {
    return if (this == 0) DASH else this.toString()
}