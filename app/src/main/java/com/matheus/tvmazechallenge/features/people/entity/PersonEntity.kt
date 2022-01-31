package com.matheus.tvmazechallenge.features.people.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonEntity(
    val id: Int,
    val name: String,
    val image: String
): Parcelable