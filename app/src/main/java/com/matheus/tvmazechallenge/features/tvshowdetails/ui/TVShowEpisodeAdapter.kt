package com.matheus.tvmazechallenge.features.tvshowdetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.TvShowEpisodeItemLayoutBinding
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.shared.base.BaseAdapter
import com.matheus.tvmazechallenge.shared.base.BaseViewHolder

class TVShowEpisodeAdapter : BaseAdapter<TVShowEpisodeEntity>() {
    override fun getViewHolder(parent: ViewGroup) = TVShowEpisodeViewHolder(
        TvShowEpisodeItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.tv_show_episode_item_layout

    inner class TVShowEpisodeViewHolder(private val binding: TvShowEpisodeItemLayoutBinding) :
        BaseViewHolder<TVShowEpisodeEntity>(binding.root) {
        override fun bind(item: TVShowEpisodeEntity, position: Int) {
            binding.apply {
                tvShowEpisode = item
                executePendingBindings()
            }
        }
    }
}