package com.matheus.tvmazechallenge.features.tvmazeshows.repository

import com.matheus.tvmazechallenge.features.tvmazeshows.datasource.TVMazeShowsDatasource
import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

class TVMazeShowRepositoryImpl(
    private val tvMazeShowsDatasource: TVMazeShowsDatasource
) : TVMazeShowsRepository {

    override suspend fun getShowsByPage(page: Int): StateData<List<TVShowEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val showsEntities = tvMazeShowsDatasource.getShowsByPage(page).map { it.toEntity() }
                StateData.Success(showsEntities)
            } catch (e: Exception) {
                when (e) {
                    is IOException, is SocketException -> Failure.noInternetConnectionFailure
                    else -> Failure.unexpectedFailure
                }
            }
        }

}