package com.matheus.tvmazechallenge.features.favorites.repository

import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.model.TVShowModel

interface FavoriteTVShowsRepository {
    suspend fun saveFavorite(tvShowModel: TVShowModel): StateData<Unit>
    suspend fun removeFavorite(tvShowModel: TVShowModel): StateData<Unit>
    suspend fun getAllFavorites(): StateData<List<TVShowEntity>>
    suspend fun getFavorite(tvShowId: Int): TVShowEntity?
}