package com.matheus.tvmazechallenge.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.TvShowItemLayoutBinding
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.BaseAdapter
import com.matheus.tvmazechallenge.shared.base.BaseViewHolder

class TVShowAdapter : BaseAdapter<TVShowEntity>() {

    lateinit var onClickListener: (tvShow: TVShowEntity) -> Unit

    override fun getViewHolder(parent: ViewGroup) = TVMazeShowViewHolder(
        TvShowItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.tv_show_item_layout

    inner class TVMazeShowViewHolder(private val binding: TvShowItemLayoutBinding) :
        BaseViewHolder<TVShowEntity>(binding.root) {
        override fun bind(item: TVShowEntity, position: Int) {
            binding.apply {
                tvshow = item
                setOnClickListener { onClickListener(item) }
                executePendingBindings()
            }
        }
    }
}