package com.matheus.tvmazechallenge.features.tvshowdetails.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.TvShowEpisodeScheduleItemLayoutBinding
import com.matheus.tvmazechallenge.shared.base.BaseAdapter
import com.matheus.tvmazechallenge.shared.base.BaseViewHolder

class TVShowEpisodeScheduleAdapter : BaseAdapter<TVShowEpisodeScheduleAdapter.EpisodeSchedule>() {

    private val episodeScheduleDays = listOf(
        EpisodeSchedule("Monday", false),
        EpisodeSchedule("Tuesday", false),
        EpisodeSchedule("Wednesday", false),
        EpisodeSchedule("Thursday", false),
        EpisodeSchedule("Friday", false),
        EpisodeSchedule("Saturday", false),
        EpisodeSchedule("Sunday", false)
    )

    override fun getViewHolder(parent: ViewGroup) = TVShowEpisodeScheduleViewHolder(
        TvShowEpisodeScheduleItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.tv_show_episode_schedule_item_layout

    inner class TVShowEpisodeScheduleViewHolder(private val binding: TvShowEpisodeScheduleItemLayoutBinding) :
        BaseViewHolder<EpisodeSchedule>(binding.root) {
        override fun bind(item: EpisodeSchedule, position: Int) {
            binding.apply {
                tvShowEpisodeScheduleTvDay.text = item.day
                tvShowEpisodeScheduleClContainer.setBackgroundResource(
                    if (item.isEnabled)
                        R.drawable.tv_show_episode_schedule_enabled_layout
                    else
                        R.drawable.tv_show_episode_schedule_disabled_layout
                )
            }
        }
    }

    fun setEpisodeScheduleItems(episodeSchedule: List<String>) {
        this.episodeScheduleDays.forEachIndexed { index, item ->
            if (episodeSchedule.contains(item.day)) {
                this.episodeScheduleDays[index].isEnabled = true
            }
            this.episodeScheduleDays[index].day =
                this.episodeScheduleDays[index].day.substring(0, 3)
        }
        addItems(this.episodeScheduleDays)
    }

    inner class EpisodeSchedule(
        var day: String,
        var isEnabled: Boolean
    )
}