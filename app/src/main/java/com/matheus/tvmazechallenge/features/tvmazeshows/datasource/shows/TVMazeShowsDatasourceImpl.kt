package com.matheus.tvmazechallenge.features.tvmazeshows.datasource.shows

import com.matheus.tvmazechallenge.shared.api.TVMazeShowsService
import com.matheus.tvmazechallenge.shared.model.TVShowModel

class TVMazeShowsDatasourceImpl(
    private val tvMazeShowsService: TVMazeShowsService
) : TVMazeShowsDatasource {
    override suspend fun getShowsByPage(page: Int): List<TVShowModel> =
        tvMazeShowsService.getShowsByPage(page)
}