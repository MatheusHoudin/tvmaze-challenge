package com.matheus.tvmazechallenge.shared.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TVShowDatabaseDAO {

    @Transaction
    @Query("SELECT * FROM tv_show WHERE id = :tvShowId")
    fun getTVShow(tvShowId: Int): List<TVShowAndScheduleDAO>
}