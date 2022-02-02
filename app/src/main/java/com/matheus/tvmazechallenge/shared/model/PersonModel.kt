package com.matheus.tvmazechallenge.shared.model

import com.google.gson.annotations.SerializedName

data class PersonModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    val url: String,
    @SerializedName("image")
    val image: ImageModel?
)