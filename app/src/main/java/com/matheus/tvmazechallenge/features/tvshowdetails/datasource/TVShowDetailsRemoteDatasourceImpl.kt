package com.matheus.tvmazechallenge.features.tvshowdetails.datasource

import com.matheus.tvmazechallenge.shared.api.TVShowsService

class TVShowDetailsRemoteDatasourceImpl(
    private val tvShowsService: TVShowsService
) : TVShowDetailsRemoteDatasource {
    override suspend fun getShowEpisodes(tvShowId: Int) =
        tvShowsService.getShowEpisodes(tvShowId)
}