package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.persondetails.entity.PersonDetailsEntity
import com.matheus.tvmazechallenge.shared.model.PersonDetailsModel

fun PersonDetailsModel.toEntity() = PersonDetailsEntity(
    creditType = this.creditType,
    tvShow = this.tvShowCredit.show.toEntity()
)