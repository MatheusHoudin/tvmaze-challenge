package com.matheus.tvmazechallenge.shared.model

data class TVShowEpisodeModel(
    val name: String,
    val season: Int,
    val number: Int,
    val summary: String?,
    val image: TVShowImageModel?
)