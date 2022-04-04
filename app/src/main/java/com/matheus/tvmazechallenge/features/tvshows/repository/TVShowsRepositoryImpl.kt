package com.matheus.tvmazechallenge.features.tvshows.repository

import com.matheus.tvmazechallenge.features.tvshows.datasource.TVShowsRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.net.SocketException
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val tvShowsRemoteDatasource: TVShowsRemoteDatasource
) : TVShowsRepository {

    override suspend fun getShowsByPage(page: Int): StateData<List<TVShowEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val showsEntities = tvShowsRemoteDatasource.getShowsByPage(page).map { it.toEntity() }
                StateData.Success(showsEntities)
            } catch (e: Exception) {
                when (e) {
                    is IOException, is SocketException -> Failure.noInternetConnectionFailure
                    else -> Failure.unexpectedFailure
                }
            }
        }

}