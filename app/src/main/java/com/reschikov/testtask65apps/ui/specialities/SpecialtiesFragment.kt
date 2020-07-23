package com.reschikov.testtask65apps.ui.specialities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reschikov.testtask65apps.KEY_SPECIALTY_CODE
import com.reschikov.testtask65apps.R
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.di.adapter.ScopeFragment
import com.reschikov.testtask65apps.ui.OnItemClickListener
import com.reschikov.testtask65apps.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_common_recycler.*
import javax.inject.Inject

@ScopeFragment
class SpecialtiesFragment : BaseFragment(R.layout.fragment_common_recycler), OnItemClickListener<Speciality> {

    @Inject lateinit var specialityAdapter : SpecialtiesAdapter

    private val observerListSpecialties by lazy {
        Observer<List<Speciality>> {
            if (it.isEmpty()) activity?.showAlertDialog(getString(R.string.warning_empty_list_received))
            else specialityAdapter.list = it
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(SpecialtiesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (viewModel as SpecialtiesViewModel).getLivDataListSpeciality().observe(viewLifecycleOwner, observerListSpecialties)
        rv_list.adapter = specialityAdapter
        rv_list.setHasFixedSize(true)
        showTitleScreen(getString(R.string.title_specialities))
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAG Specialti factory", System.identityHashCode(factory).toString())
        Log.i("TAG Specialti viewModel", System.identityHashCode(viewModel).toString())
    }

    override fun onItemClick(item: Speciality) {
        navController.navigate(R.id.action_specialtiesFragment_to_employeeCardsFragment, Bundle().apply {
            putParcelable(KEY_SPECIALTY_CODE, item)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_list.adapter = null
    }
}