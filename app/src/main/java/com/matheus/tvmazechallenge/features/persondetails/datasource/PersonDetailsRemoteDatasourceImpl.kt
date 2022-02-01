package com.matheus.tvmazechallenge.features.persondetails.datasource

import com.matheus.tvmazechallenge.shared.api.PeopleService

class PersonDetailsRemoteDatasourceImpl(
    private val peopleService: PeopleService
) : PersonDetailsRemoteDatasource {

    override suspend fun getPersonCastCredits(personId: Int) =
        peopleService.getPersonCastCredits(personId)
}