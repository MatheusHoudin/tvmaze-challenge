package com.matheus.tvmazechallenge.shared.dao

import androidx.room.*

@Dao
interface TVShowDatabaseDAO {

    @Transaction
    @Query("SELECT * FROM tv_show ORDER BY name")
    fun getAll(): List<TVShowDAO>

    @Insert
    fun saveFavorite(tvShow: TVShowDAO)

    @Delete
    fun removeFavorite(tvShow: TVShowDAO)

    @Query("SELECT * FROM tv_show WHERE id = :tvShowId")
    fun getFavorite(tvShowId: Int): TVShowDAO?
}