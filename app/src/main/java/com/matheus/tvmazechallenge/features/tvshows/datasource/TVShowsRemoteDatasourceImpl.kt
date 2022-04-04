package com.matheus.tvmazechallenge.features.tvshows.datasource

import com.matheus.tvmazechallenge.shared.api.TVShowsService
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import javax.inject.Inject

class TVShowsRemoteDatasourceImpl @Inject constructor(
    private val tvShowsService: TVShowsService
) : TVShowsRemoteDatasource {
    override suspend fun getShowsByPage(page: Int): List<TVShowModel> =
        tvShowsService.getShowsByPage(page)
}