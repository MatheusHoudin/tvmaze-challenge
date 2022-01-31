package com.matheus.tvmazechallenge.features.tvshows.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowEntity(
    val id: Int,
    val name: String,
    val poster: String,
    val url: String,
    val schedule: TVShowScheduleEntity,
    val genres: List<String>,
    val summary: String
): Parcelable