package com.matheus.tvmazechallenge.shared.model

data class TVShowModel(
    val id: Int,
    val name: String,
    val url: String,
    val image: ImageModel?,
    val schedule: TVShowScheduleModel,
    val genres: List<String>,
    val summary: String?
)