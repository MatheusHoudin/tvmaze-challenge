package com.matheus.tvmazechallenge.features.tvshowdetails.datasource

import com.matheus.tvmazechallenge.shared.model.TVShowEpisodeModel

interface TVShowDetailsDatasource {
    suspend fun getShowEpisodes(tvShowId: Int): List<TVShowEpisodeModel>
}