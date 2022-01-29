package com.matheus.tvmazechallenge.features.tvmazeshows.datasource

import com.matheus.tvmazechallenge.shared.model.TVShowModel

interface TVMazeShowsDatasource {
    suspend fun getShowsByPage(page: Int): List<TVShowModel>
}