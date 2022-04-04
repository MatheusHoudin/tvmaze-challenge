package com.matheus.tvmazechallenge.features.people.datasource

import com.matheus.tvmazechallenge.shared.api.PeopleService
import javax.inject.Inject

class PeopleRemoteDatasourceImpl @Inject constructor(
    private val peopleService: PeopleService
) : PeopleRemoteDatasource {

    override suspend fun getPeopleByPage(page: Int) = peopleService.getPeopleByPage(page)
}