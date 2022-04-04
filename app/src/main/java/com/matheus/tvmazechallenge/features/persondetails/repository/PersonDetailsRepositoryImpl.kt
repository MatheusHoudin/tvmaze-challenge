package com.matheus.tvmazechallenge.features.persondetails.repository

import com.matheus.tvmazechallenge.features.persondetails.datasource.PersonDetailsRemoteDatasource
import com.matheus.tvmazechallenge.features.persondetails.entity.CastCreditsEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.net.SocketException
import javax.inject.Inject

class PersonDetailsRepositoryImpl @Inject constructor(
    private val personDetailsRemoteDatasource: PersonDetailsRemoteDatasource
) : PersonDetailsRepository {

    override suspend fun getPersonCastCredits(personId: Int): StateData<List<CastCreditsEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val personCastCreditsEntities =
                    personDetailsRemoteDatasource.getPersonCastCredits(personId).map { it.toEntity() }

                StateData.Success(personCastCreditsEntities)
            } catch (e: Exception) {
                when (e) {
                    is IOException, is SocketException -> Failure.noInternetConnectionFailure
                    else -> Failure.getCastCreditsFailure
                }
            }
        }
}