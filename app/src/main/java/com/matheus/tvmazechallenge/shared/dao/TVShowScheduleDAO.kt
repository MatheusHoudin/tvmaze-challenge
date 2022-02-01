package com.matheus.tvmazechallenge.shared.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_schedule")
data class TVShowScheduleDAO(
    @PrimaryKey val tvShowId: Int,
    val time: String,
    val days: List<String>
)