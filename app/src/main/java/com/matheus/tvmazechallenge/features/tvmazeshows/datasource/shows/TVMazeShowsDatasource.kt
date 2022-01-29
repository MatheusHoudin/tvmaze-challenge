package com.matheus.tvmazechallenge.features.tvmazeshows.datasource.shows

import com.matheus.tvmazechallenge.shared.model.TVShowModel

interface TVMazeShowsDatasource {
    suspend fun getShowsByPage(page: Int): List<TVShowModel>
}