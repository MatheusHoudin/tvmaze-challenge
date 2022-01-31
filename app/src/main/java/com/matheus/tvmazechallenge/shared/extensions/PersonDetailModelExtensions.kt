package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.persondetails.entity.CastCreditsEntity
import com.matheus.tvmazechallenge.shared.model.CastCreditsModel

fun CastCreditsModel.toEntity() = CastCreditsEntity(
    creditType = this.creditType,
    tvShow = this.tvShowCredit.show.toEntity()
)