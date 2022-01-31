package com.matheus.tvmazechallenge.shared.model

import com.google.gson.annotations.SerializedName

data class PeopleModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: ImageModel?
)