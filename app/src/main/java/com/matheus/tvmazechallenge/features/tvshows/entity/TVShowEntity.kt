package com.matheus.tvmazechallenge.features.tvshows.entity

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
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
): Parcelable {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}