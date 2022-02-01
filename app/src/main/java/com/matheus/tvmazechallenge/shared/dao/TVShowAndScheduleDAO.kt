package com.matheus.tvmazechallenge.shared.dao

import androidx.room.Embedded
import androidx.room.Relation

data class TVShowAndScheduleDAO(
    @Embedded val tvShow: TVShowDAO,
    @Relation(
        parentColumn = "id",
        entityColumn = "tvShowId"
    )
    val tvShowScheduleDAO: TVShowScheduleDAO
)