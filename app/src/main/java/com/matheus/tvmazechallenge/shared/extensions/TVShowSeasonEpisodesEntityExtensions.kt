package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity

fun List<TVShowSeasonEpisodesEntity>.toSeasonList() = this.map { "Season ${it.season}" }