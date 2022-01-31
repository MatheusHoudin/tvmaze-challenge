package com.matheus.tvmazechallenge.features.people.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.ImageDescriptionItemLayoutBinding
import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.shared.base.BaseAdapter
import com.matheus.tvmazechallenge.shared.base.BaseViewHolder

class PeopleAdapter : BaseAdapter<PersonEntity>() {

    lateinit var onClickListener: (personEntity: PersonEntity) -> Unit

    override fun getViewHolder(parent: ViewGroup) = PeopleViewHolder(
        ImageDescriptionItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.image_description_item_layout

    inner class PeopleViewHolder(private val binding: ImageDescriptionItemLayoutBinding) :
        BaseViewHolder<PersonEntity>(binding.root) {
        override fun bind(item: PersonEntity, position: Int) {
            binding.apply {
                image = item.image
                description = item.name
                setOnClickListener { onClickListener(item) }
                executePendingBindings()
            }
        }
    }
}