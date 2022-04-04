package com.matheus.tvmazechallenge.features.tvshowdetails.datasource

import com.matheus.tvmazechallenge.shared.api.TVShowsService
import javax.inject.Inject

class TVShowDetailsRemoteDatasourceImpl @Inject constructor(
    private val tvShowsService: TVShowsService
) : TVShowDetailsRemoteDatasource {
    override suspend fun getShowEpisodes(tvShowId: Int) =
        tvShowsService.getShowEpisodes(tvShowId)
}