package com.matheus.tvmazechallenge.features.people.datasource

import com.matheus.tvmazechallenge.shared.model.PeopleModel

interface PeopleRemoteDatasource {
    suspend fun getPeopleByPage(page: Int): List<PeopleModel>
}