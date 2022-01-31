package com.matheus.tvmazechallenge.features.persondetails.datasource

import com.matheus.tvmazechallenge.shared.model.PersonDetailsModel

interface PersonDetailsDatasource {
    suspend fun getPersonCastCredits(personId: Int): List<PersonDetailsModel>
}