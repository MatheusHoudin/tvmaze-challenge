package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.model.TVShowEpisodeModel

fun List<TVShowEpisodeModel>.toTVShowSeasonEpisodesEntity(): List<TVShowSeasonEpisodesEntity> {
    val tvShowSeasonEpisodes = mutableListOf<TVShowSeasonEpisodesEntity>()
    var lastAddedSeasonNumber: Int = -1

    this.forEach {
        if (thereIsNewSeasonToBeAdded(it.season, lastAddedSeasonNumber)) {
            tvShowSeasonEpisodes.add(TVShowSeasonEpisodesEntity(season = it.season.toString()))
        }

        lastAddedSeasonNumber = it.season
        tvShowSeasonEpisodes[tvShowSeasonEpisodes.size - 1].episodes.add(it.toEntity())
    }

    return tvShowSeasonEpisodes
}

private fun thereIsNewSeasonToBeAdded(seasonNumber: Int, lastAddedSeason: Int): Boolean =
    seasonNumber != lastAddedSeason

fun TVShowEpisodeModel.toEntity() = TVShowEpisodeEntity(
    name = this.name,
    number = this.number.toString(),
    season = this.season.toString(),
    summary = this.summary.orEmpty(),
    image = this.image?.medium ?: Constants.DEFAULT_TVMAZE_IMAGE
)