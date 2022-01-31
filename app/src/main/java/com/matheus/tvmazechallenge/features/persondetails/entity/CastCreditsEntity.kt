package com.matheus.tvmazechallenge.features.persondetails.entity

import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity

data class CastCreditsEntity(
    val creditType: String,
    val tvShow: TVShowEntity
)