package com.matheus.tvmazechallenge.features.tvshows.datasource

import com.matheus.tvmazechallenge.shared.api.TVShowsService
import com.matheus.tvmazechallenge.shared.model.TVShowModel

class TVShowsDatasourceImpl(
    private val tvShowsService: TVShowsService
) : TVShowsDatasource {
    override suspend fun getShowsByPage(page: Int): List<TVShowModel> =
        tvShowsService.getShowsByPage(page)
}