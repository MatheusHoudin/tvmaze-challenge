package com.matheus.tvmazechallenge.features.people.respository

import com.matheus.tvmazechallenge.features.people.datasource.PeopleRemoteDatasource
import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

class PeopleRepositoryImpl(
    private val peopleRemoteDatasource: PeopleRemoteDatasource
) : PeopleRepository {
    override suspend fun getPeopleByPage(page: Int): StateData<List<PersonEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val peopleEntities = peopleRemoteDatasource.getPeopleByPage(page).map { it.toEntity() }
                StateData.Success(peopleEntities)
            } catch (e: Exception) {
                when (e) {
                    is IOException, is SocketException -> Failure.noInternetConnectionFailure
                    else -> Failure.getPeopleFailure
                }
            }
        }
}