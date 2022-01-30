package com.matheus.tvmazechallenge.features.tvshows.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowScheduleEntity(
    val time: String,
    val days: List<String>
): Parcelable