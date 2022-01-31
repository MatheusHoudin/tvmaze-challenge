package com.matheus.tvmazechallenge.features.people.datasource

import com.matheus.tvmazechallenge.shared.api.PeopleService

class PeopleDatasourceImpl(
    private val peopleService: PeopleService
) : PeopleDatasource {

    override suspend fun getPeopleByPage(page: Int) = peopleService.getPeopleByPage(page)
}