package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.model.PeopleModel

fun PeopleModel.toEntity() = PersonEntity(
    id = this.id,
    name = this.name,
    image = this.image?.medium ?: Constants.DEFAULT_TVMAZE_IMAGE,
)