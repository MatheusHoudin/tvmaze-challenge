package com.matheus.tvmazechallenge.features.favorites.datasource

import com.matheus.tvmazechallenge.shared.dao.TVShowDatabaseDAO
import com.matheus.tvmazechallenge.shared.extensions.toDAO
import com.matheus.tvmazechallenge.shared.extensions.toModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel

class FavoriteTVShowsLocalDatasourceImpl(
    private val tvShowDatabaseDAO: TVShowDatabaseDAO
) : FavoriteTVShowsLocalDatasource {

    override suspend fun saveFavorite(tvShow: TVShowModel) =
        tvShowDatabaseDAO.saveFavorite(tvShow.toDAO())

    override suspend fun removeFavorite(tvShow: TVShowModel) =
        tvShowDatabaseDAO.removeFavorite(tvShow.toDAO())

    override suspend fun getAll() =
        tvShowDatabaseDAO.getAll().map { it.toModel() }

    override suspend fun getFavorite(tvShowId: Int) =
        tvShowDatabaseDAO.getFavorite(tvShowId)?.toModel()
}