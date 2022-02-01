package com.matheus.tvmazechallenge.shared.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TVShowDAO(
    @PrimaryKey val id: Int,
    val name: String,
    val poster: String?,
    val url: String,
    val scheduleTime: String,
    val scheduleDays: List<String>,
    val genres: List<String>,
    val summary: String?
)