package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.model.TVShowEpisodeModel

fun List<TVShowEpisodeModel>.toTVShowSeasonEpisodesEntity(): List<TVShowSeasonEpisodesEntity> {
    val tvShowSeasonEpisodes = mutableListOf<TVShowSeasonEpisodesEntity>()

    this.forEach {
        if (thereIsNewSeasonToBeAdded(tvShowSeasonEpisodes, it.season)) {
            tvShowSeasonEpisodes.add(TVShowSeasonEpisodesEntity(season = it.season.toString()))
        }

        tvShowSeasonEpisodes[it.season - 1].episodes.add(it.toEntity())
    }

    return tvShowSeasonEpisodes
}

private fun thereIsNewSeasonToBeAdded(
    currentSeasons: MutableList<TVShowSeasonEpisodesEntity>,
    seasonNumber: Int
): Boolean = seasonNumber > currentSeasons.size

fun TVShowEpisodeModel.toEntity() = TVShowEpisodeEntity(
    name = this.name,
    number = this.number.toString(),
    season = this.season.toString(),
    summary = this.summary,
    image = this.image?.medium ?: Constants.DEFAULT_TVMAZE_IMAGE
)