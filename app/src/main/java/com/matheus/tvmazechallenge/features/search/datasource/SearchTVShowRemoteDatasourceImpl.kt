package com.matheus.tvmazechallenge.features.search.datasource

import com.matheus.tvmazechallenge.shared.api.TVShowsService

class SearchTVShowRemoteDatasourceImpl(
    private val showsService: TVShowsService
) : SearchTVShowRemoteDatasource {

    override suspend fun searchShows(search: String) = showsService.searchShows(search)
}