package com.reschikov.testtask65apps.ui.specialities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reschikov.testtask65apps.R
import com.reschikov.testtask65apps.cache.model.Speciality
import com.reschikov.testtask65apps.ui.OnItemClickListener
import com.reschikov.testtask65apps.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_specialty.view.*

class SpecialtiesAdapter (onItemClickListener: OnItemClickListener<Speciality>) :
    BaseAdapter<Speciality>(onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Speciality> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specialty, parent, false)
        return SpecialityViewHolder(view)
    }

    class SpecialityViewHolder(view : View) : BaseViewHolder<Speciality>(view) {

        override fun show(item: Speciality) {
            itemView.tv_specialty.text = item.name
        }
    }
}