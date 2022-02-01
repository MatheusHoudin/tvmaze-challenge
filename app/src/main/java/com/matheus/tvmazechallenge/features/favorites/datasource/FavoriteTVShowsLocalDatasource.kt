package com.matheus.tvmazechallenge.features.favorites.datasource

import com.matheus.tvmazechallenge.shared.model.TVShowModel

interface FavoriteTVShowsLocalDatasource {
    suspend fun saveFavorite(tvShow: TVShowModel)
    suspend fun removeFavorite(tvShow: TVShowModel)
    suspend fun getAll(): List<TVShowModel>
    suspend fun getFavorite(tvShowId: Int): TVShowModel?
}