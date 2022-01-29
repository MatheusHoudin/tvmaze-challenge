package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.model.TVShowModel

fun TVShowModel.toEntity() = TVShowEntity(
    id = this.id,
    name = this.name,
    poster = this.image.medium
)