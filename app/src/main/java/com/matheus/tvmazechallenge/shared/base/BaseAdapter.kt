package com.matheus.tvmazechallenge.shared.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ObjectType> : RecyclerView.Adapter<BaseViewHolder<ObjectType>>() {

    private val dataSet = mutableListOf<ObjectType>()

    abstract fun getViewHolder(parent: ViewGroup): BaseViewHolder<ObjectType>

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getViewHolder(parent)

    override fun onBindViewHolder(holder: BaseViewHolder<ObjectType>, position: Int) =
        holder.bind(dataSet[position], position)

    override fun getItemCount() = dataSet.size

    fun addItems(values: List<ObjectType>) {
        dataSet.apply {
            clear()
            addAll(values)
        }
        notifyDataSetChanged()
    }
}