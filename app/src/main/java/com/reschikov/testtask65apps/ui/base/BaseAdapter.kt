package com.reschikov.testtask65apps.ui.base

import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reschikov.testtask65apps.ui.OnItemClickListener
import kotlinx.android.extensions.LayoutContainer

abstract class BaseAdapter<T>(protected val onItemClickListener: OnItemClickListener<T>) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    var list: List<T> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.show(list[position])
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnTouchListener { v, event ->
            onItemClickListener.onItemClick(list[holder.adapterPosition])
            return@setOnTouchListener v.performClick()
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<T>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnTouchListener(null)
    }

    abstract class BaseViewHolder<T>(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        abstract fun show (item : T)
    }
}