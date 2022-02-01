package com.matheus.tvmazechallenge.features.tvshowdetails.repository

import com.matheus.tvmazechallenge.features.tvshowdetails.datasource.TVShowDetailsRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toTVShowSeasonEpisodesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

class TVShowDetailsRepositoryImpl(
    private val tvShowDetailsRemoteDatasource: TVShowDetailsRemoteDatasource
) : TVShowDetailsRepository {
    override suspend fun getShowEpisodes(tvShowId: Int): StateData<List<TVShowSeasonEpisodesEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val showEpisodes = tvShowDetailsRemoteDatasource.getShowEpisodes(tvShowId)
                StateData.Success(showEpisodes.toTVShowSeasonEpisodesEntity())
            } catch (e: Exception) {
                when (e) {
                    is IOException, is SocketException -> Failure.noInternetConnectionFailure
                    else -> Failure.getEpisodesFailure
                }
            }
        }
}