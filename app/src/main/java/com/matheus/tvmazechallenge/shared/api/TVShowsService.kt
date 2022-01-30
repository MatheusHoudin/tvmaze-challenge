package com.matheus.tvmazechallenge.shared.api

import com.matheus.tvmazechallenge.shared.model.SearchTVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowEpisodeModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowsService {

    @GET("/shows")
    suspend fun getShowsByPage(@Query("page") page: Int): List<TVShowModel>

    @GET("/search/shows")
    suspend fun searchShows(@Query("q") search: String): List<SearchTVShowModel>

    @GET("/shows/{tvShowId}/episodes")
    suspend fun getShowEpisodes(@Path("tvShowId") tvShowId: Int): List<TVShowEpisodeModel>
}