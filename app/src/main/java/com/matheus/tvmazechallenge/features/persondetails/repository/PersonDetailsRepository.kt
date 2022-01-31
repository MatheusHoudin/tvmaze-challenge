package com.matheus.tvmazechallenge.features.persondetails.repository

import com.matheus.tvmazechallenge.features.persondetails.entity.PersonDetailsEntity
import com.matheus.tvmazechallenge.shared.base.StateData

interface PersonDetailsRepository {
    suspend fun getPersonCastCredits(personId: Int): StateData<List<PersonDetailsEntity>>
}