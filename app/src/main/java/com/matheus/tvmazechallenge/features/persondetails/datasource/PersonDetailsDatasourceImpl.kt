package com.matheus.tvmazechallenge.features.persondetails.datasource

import com.matheus.tvmazechallenge.shared.api.PeopleService

class PersonDetailsDatasourceImpl(
    private val peopleService: PeopleService
) : PersonDetailsDatasource {

    override suspend fun getPersonCastCredits(personId: Int) =
        peopleService.getPersonCastCredits(personId)
}