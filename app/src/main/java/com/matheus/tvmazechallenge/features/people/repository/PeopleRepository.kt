package com.matheus.tvmazechallenge.features.people.repository

import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.shared.base.StateData

interface PeopleRepository {
    suspend fun getPeopleByPage(page: Int): StateData<List<PersonEntity>>
}