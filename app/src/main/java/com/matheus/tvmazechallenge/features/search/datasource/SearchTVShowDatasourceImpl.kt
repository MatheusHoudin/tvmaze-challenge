package com.matheus.tvmazechallenge.features.search.datasource

import com.matheus.tvmazechallenge.shared.api.TVShowsService

class SearchTVShowDatasourceImpl(
    private val showsService: TVShowsService
) : SearchTVShowDatasource {

    override suspend fun searchShows(search: String) = showsService.searchShows(search)
}