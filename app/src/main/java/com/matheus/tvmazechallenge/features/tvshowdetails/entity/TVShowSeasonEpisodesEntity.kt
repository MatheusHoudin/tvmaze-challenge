package com.matheus.tvmazechallenge.features.tvshowdetails.entity

data class TVShowSeasonEpisodesEntity(
    val season: String = "",
    var episodes: MutableList<TVShowEpisodeEntity> = mutableListOf()
)