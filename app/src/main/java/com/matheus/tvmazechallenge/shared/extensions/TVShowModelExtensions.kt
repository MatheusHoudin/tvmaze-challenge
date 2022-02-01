package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.dao.TVShowDAO
import com.matheus.tvmazechallenge.shared.model.TVShowModel

fun TVShowModel.toEntity() = TVShowEntity(
    id = this.id,
    name = this.name,
    url = this.url,
    poster = this.image?.medium ?: Constants.DEFAULT_TVMAZE_IMAGE,
    schedule = TVShowScheduleEntity(
        time = this.schedule.time,
        days = this.schedule.days
    ),
    genres = this.genres,
    summary = this.summary.orEmpty()
)

fun TVShowModel.toDAO() = TVShowDAO(
    id = this.id,
    name = this.name,
    poster = this.image?.medium,
    scheduleTime = this.schedule.time,
    scheduleDays = this.schedule.days,
    url = this.url,
    genres = this.genres,
    summary = this.summary
)