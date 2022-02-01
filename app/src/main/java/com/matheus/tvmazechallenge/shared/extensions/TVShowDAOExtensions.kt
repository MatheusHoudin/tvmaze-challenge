package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.shared.dao.TVShowDAO
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowScheduleModel

fun TVShowDAO.toModel() = TVShowModel(
    id = this.id,
    name = this.name,
    url = this.url,
    image = ImageModel(medium = this.poster, original = this.poster),
    schedule = TVShowScheduleModel(time = this.scheduleTime, days = this.scheduleDays),
    genres = this.genres,
    summary = this.summary
)