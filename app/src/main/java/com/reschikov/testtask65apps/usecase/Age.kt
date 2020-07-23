package com.reschikov.testtask65apps.usecase

import java.util.*
import javax.inject.Inject

class Age @Inject constructor() : Calculated {

    override fun calculateAge(date: Long?): Int {
        return date?.let {
            val current = Calendar.getInstance()
            val birthday = Calendar.getInstance()
            birthday.timeInMillis = it
            getDifference(birthday, current)
        } ?: 0
    }

    private fun getDifference(birthday : Calendar, current : Calendar) : Int{
        val year = current.get(Calendar.YEAR) - birthday.get(Calendar.YEAR)
        if (current.get(Calendar.MONTH) > birthday.get(Calendar.MONTH)) return year
        if (current.get(Calendar.MONTH) == birthday.get(Calendar.MONTH) &&
            current.get(Calendar.DATE) >= birthday.get(Calendar.DATE)) return year
        return year - 1
    }
}