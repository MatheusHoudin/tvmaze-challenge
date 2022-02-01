package com.matheus.tvmazechallenge.features.search.repository

import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

class SearchTVShowRepositoryImpl(
    private val searchTVShowRemoteDatasource: SearchTVShowRemoteDatasource
) : SearchTVShowRepository {

    override suspend fun searchShows(search: String): StateData<List<TVShowEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val foundShows = searchTVShowRemoteDatasource.searchShows(search).map { it.show.toEntity() }
                StateData.Success(foundShows)
            } catch (e: Exception) {
                when (e) {
                    is IOException, is SocketException -> Failure.noInternetConnectionFailure
                    else -> Failure.unexpectedFailure
                }
            }
        }
}