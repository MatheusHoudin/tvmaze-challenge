package com.matheus.tvmazechallenge.features.search.datasource

import com.matheus.tvmazechallenge.shared.model.SearchTVShowModel

interface SearchTVShowRemoteDatasource {
    suspend fun searchShows(search: String): List<SearchTVShowModel>
}