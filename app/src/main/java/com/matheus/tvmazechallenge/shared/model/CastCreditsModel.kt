package com.matheus.tvmazechallenge.shared.model

import com.google.gson.annotations.SerializedName

data class CastCreditsModel(
    @SerializedName("type")
    val creditType: String,
    @SerializedName("_embedded")
    val tvShowCredit: TVShowCreditModel
)