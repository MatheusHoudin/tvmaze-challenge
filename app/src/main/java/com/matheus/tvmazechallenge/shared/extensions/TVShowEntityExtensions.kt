package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowScheduleModel

fun TVShowEntity.toModel() = TVShowModel(
    id = this.id,
    name = this.name,
    url = this.url,
    image = ImageModel(medium = this.poster, original = ""),
    schedule = TVShowScheduleModel(time = this.schedule.time, days = this.schedule.days),
    genres = this.genres,
    summary = this.summary
)