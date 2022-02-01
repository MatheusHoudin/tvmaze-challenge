package com.matheus.tvmazechallenge.features.persondetails.datasource

import com.matheus.tvmazechallenge.shared.model.CastCreditsModel

interface PersonDetailsRemoteDatasource {
    suspend fun getPersonCastCredits(personId: Int): List<CastCreditsModel>
}