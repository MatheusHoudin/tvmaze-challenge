package com.matheus.tvmazechallenge.features.search.datasource

import com.matheus.tvmazechallenge.shared.api.TVMazeShowsService

class SearchTVShowDatasourceImpl(
    private val showsService: TVMazeShowsService
) : SearchTVShowDatasource {

    override suspend fun searchShows(search: String) = showsService.searchShows(search)
}