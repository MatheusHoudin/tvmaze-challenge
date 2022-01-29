package com.matheus.tvmazechallenge.features.tvmazeshows.repository.shows

import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.model.TVShowModel

interface TVMazeShowsRepository {
    suspend fun getShowsByPage(page: Int): StateData<List<TVShowEntity>>
}