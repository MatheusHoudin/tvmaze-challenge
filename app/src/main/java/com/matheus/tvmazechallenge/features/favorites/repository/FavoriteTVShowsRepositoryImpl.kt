package com.matheus.tvmazechallenge.features.favorites.repository

import com.matheus.tvmazechallenge.features.favorites.datasource.FavoriteTVShowsLocalDatasource
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toEntity
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class FavoriteTVShowsRepositoryImpl(
    private val favoriteTVShowsLocalDatasource: FavoriteTVShowsLocalDatasource
) : FavoriteTVShowsRepository {

    override suspend fun saveFavorite(tvShowModel: TVShowModel) = withContext(Dispatchers.IO) {
        try {
            StateData.Success(favoriteTVShowsLocalDatasource.saveFavorite(tvShowModel))
        } catch (e: Exception) {
            Failure.unexpectedFailure
        }
    }

    override suspend fun removeFavorite(tvShowModel: TVShowModel) = withContext(Dispatchers.IO) {
        try {
            StateData.Success(favoriteTVShowsLocalDatasource.removeFavorite(tvShowModel))
        } catch (e: Exception) {
            Failure.unexpectedFailure
        }
    }

    override suspend fun getAllFavorites(): StateData<List<TVShowEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val favoriteTVShows =
                    favoriteTVShowsLocalDatasource.getAll().map { it.toEntity() }

                StateData.Success(favoriteTVShows)
            } catch (e: Exception) {
                Failure.unexpectedFailure
            }
        }

    override suspend fun getFavorite(tvShowId: Int): TVShowEntity? =
        withContext(Dispatchers.IO) {
            favoriteTVShowsLocalDatasource.getFavorite(tvShowId)?.toEntity()
        }
}