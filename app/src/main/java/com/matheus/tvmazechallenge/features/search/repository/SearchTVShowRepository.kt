package com.matheus.tvmazechallenge.features.search.repository

import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData

interface SearchTVShowRepository {
    suspend fun searchShows(search: String): StateData<List<TVShowEntity>>
}