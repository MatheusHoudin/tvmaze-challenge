package com.matheus.tvmazechallenge.features.tvshowdetails.datasource

import com.matheus.tvmazechallenge.shared.api.TVShowsService

class TVShowDetailsDatasourceImpl(
    private val tvShowsService: TVShowsService
) : TVShowDetailsDatasource {
    override suspend fun getShowEpisodes(tvShowId: Int) =
        tvShowsService.getShowEpisodes(tvShowId)
}