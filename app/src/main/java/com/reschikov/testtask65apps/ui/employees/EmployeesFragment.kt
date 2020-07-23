package com.reschikov.testtask65apps.ui.employees

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reschikov.testtask65apps.KEY_EMPLOYEE_CARD
import com.reschikov.testtask65apps.KEY_SPECIALTY_CODE
import com.reschikov.testtask65apps.R
import com.reschikov.testtask65apps.cache.model.Employee
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.di.adapter.ScopeFragment
import com.reschikov.testtask65apps.ui.OnItemClickListener
import com.reschikov.testtask65apps.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_common_recycler.*
import javax.inject.Inject

@ScopeFragment
class EmployeesFragment : BaseFragment(R.layout.fragment_common_recycler), OnItemClickListener<Employee>{

    @Inject lateinit var employeeAdapter : EmployeesAdapter

    private val observerListEmployee by lazy {
        Observer<List<Employee>> {
            if (it.isEmpty()) activity?.showAlertDialog(getString(R.string.warning_empty_list_received))
            else employeeAdapter.list = it
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(EmployeesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list.adapter = employeeAdapter
        rv_list.setHasFixedSize(true)
        (viewModel as EmployeesViewModel).getDataListEmployeeCards().observe(viewLifecycleOwner, observerListEmployee)
        savedInstanceState ?: run{
            arguments?.let {bundle ->
                bundle.getParcelable<Speciality>(KEY_SPECIALTY_CODE)?.let {
                    (viewModel as EmployeesViewModel).getListEmployeeCards(it.id)
                    showTitleScreen(it.name)
                }
            }
        }
    }

    override fun onItemClick(item: Employee) {
        navController.navigate(R.id.action_employeeCardsFragment_to_employeeFragment, Bundle().apply {
            putLong(KEY_EMPLOYEE_CARD, item.id)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_list.adapter = null
    }
}