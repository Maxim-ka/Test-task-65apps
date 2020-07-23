package com.reschikov.testtask65apps.ui.employeecard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reschikov.testtask65apps.DASH
import com.reschikov.testtask65apps.KEY_EMPLOYEE_CARD
import com.reschikov.testtask65apps.R
import com.reschikov.testtask65apps.entity.dot.EmployeeCard
import com.reschikov.testtask65apps.getTextAge
import com.reschikov.testtask65apps.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_employee_card.*
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

private const val REQUIRED_PATTERN = "dd.MM.yyyy"

class EmployeeCardFragment : BaseFragment(R.layout.fragment_employee_card){

    private val observerEmployeeCard by lazy {
        Observer<EmployeeCard> { card ->
            card.employee.also {
                tiet_first_name.setText(it.fName)
                tiet_last_name.setText(it.lName)
                tiet_birthday.setText(getStringBirthday(it.birthday))
                tiet_age.setText(it.age.getTextAge())
                tiet_specialty.setText(getSpecialties(card.specialityName))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(EmployeeCardViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (viewModel as EmployeeCardViewModel).getEmployeeCardLiveData().observe(viewLifecycleOwner, observerEmployeeCard)
        savedInstanceState ?: run {
            arguments?.let { (viewModel as EmployeeCardViewModel).getEmployeeCard(it.getLong(KEY_EMPLOYEE_CARD)) }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getStringBirthday(date : Long?) : String {
        return date?.let { "${SimpleDateFormat(REQUIRED_PATTERN).format(Date(it))} г." } ?: DASH
    }

    private fun getSpecialties(list: List<String>) : String {
        val sb  = StringBuilder()
        list.forEach {
            sb.append(it)
            if (list.last() != it) sb.append("\n")
        }
        return sb.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showTitleScreen(getString(R.string.title_employee_card))
    }
}