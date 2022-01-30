package com.matheus.tvmazechallenge.features.tvshows.repository

import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData

interface TVShowsRepository {
    suspend fun getShowsByPage(page: Int): StateData<List<TVShowEntity>>
}