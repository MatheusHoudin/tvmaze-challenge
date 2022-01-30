package com.matheus.tvmazechallenge.features.tvshowdetails.repository

import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.shared.base.StateData

interface TVShowDetailsRepository {
    suspend fun getShowEpisodes(tvShowId: Int): StateData<List<TVShowSeasonEpisodesEntity>>
}