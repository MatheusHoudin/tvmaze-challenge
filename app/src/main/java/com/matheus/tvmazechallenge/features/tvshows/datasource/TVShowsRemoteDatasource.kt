package com.matheus.tvmazechallenge.features.tvshows.datasource

import com.matheus.tvmazechallenge.shared.model.TVShowModel

interface TVShowsRemoteDatasource {
    suspend fun getShowsByPage(page: Int): List<TVShowModel>
}