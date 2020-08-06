package com.reschikov.testtask65apps.ui.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reschikov.testtask65apps.R
import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.getTextAge
import com.reschikov.testtask65apps.ui.OnItemClickListener
import com.reschikov.testtask65apps.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeesAdapter(onItemClickListener: OnItemClickListener<Employee>) :
    BaseAdapter<Employee>(onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Employee> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeCardViewHolder(view)
    }

    class EmployeeCardViewHolder(view: View) : BaseViewHolder<Employee>(view){

        override fun show(item: Employee) {
            with(itemView){
                item.also {
                    tiet_first_name.setText(it.fName)
                    tiet_last_name.setText(it.lName)
                    val textAge = it.age.getTextAge()
                    tiet_age.setText(textAge)
                }
            }
        }
    }
}