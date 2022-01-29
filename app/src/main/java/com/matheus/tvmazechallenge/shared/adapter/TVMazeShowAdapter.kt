package com.matheus.tvmazechallenge.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.TvmazeShowItemLayoutBinding
import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.BaseAdapter
import com.matheus.tvmazechallenge.shared.base.BaseViewHolder

class TVMazeShowAdapter : BaseAdapter<TVShowEntity>() {

    override fun getViewHolder(parent: ViewGroup) = TVMazeShowViewHolder(
        TvmazeShowItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.tvmaze_show_item_layout

    inner class TVMazeShowViewHolder(private val binding: TvmazeShowItemLayoutBinding) :
        BaseViewHolder<TVShowEntity>(binding.root) {
        override fun bind(item: TVShowEntity, position: Int) {
            binding.apply {
                tvshow = item
                executePendingBindings()
            }
        }
    }
}