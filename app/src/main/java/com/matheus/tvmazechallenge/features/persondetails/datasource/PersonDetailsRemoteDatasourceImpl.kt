package com.matheus.tvmazechallenge.features.persondetails.datasource

import com.matheus.tvmazechallenge.shared.api.PeopleService
import javax.inject.Inject

class PersonDetailsRemoteDatasourceImpl @Inject constructor(
    private val peopleService: PeopleService
) : PersonDetailsRemoteDatasource {

    override suspend fun getPersonCastCredits(personId: Int) =
        peopleService.getPersonCastCredits(personId)
}