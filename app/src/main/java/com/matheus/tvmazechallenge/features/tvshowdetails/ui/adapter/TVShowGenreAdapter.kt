package com.matheus.tvmazechallenge.features.tvshowdetails.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.TvShowGenreItemLayoutBinding
import com.matheus.tvmazechallenge.shared.base.BaseAdapter
import com.matheus.tvmazechallenge.shared.base.BaseViewHolder

class TVShowGenreAdapter : BaseAdapter<String>() {
    override fun getViewHolder(parent: ViewGroup) = TVShowGenreViewHolder(
        TvShowGenreItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.tv_show_genre_item_layout

    inner class TVShowGenreViewHolder(private val binding: TvShowGenreItemLayoutBinding) :
        BaseViewHolder<String>(binding.root) {
        override fun bind(item: String, position: Int) {
            binding.apply {
                tvShowGenreTvGenre.text = item
                executePendingBindings()
            }
        }
    }
}