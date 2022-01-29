package com.matheus.tvmazechallenge.shared.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ObjectType>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: ObjectType, position: Int)
}