package com.matheus.tvmazechallenge.shared.api

import com.matheus.tvmazechallenge.shared.model.TVShowModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TVMazeShowsService {

    @GET("/shows")
    suspend fun getShowsByPage(@Query("page") page: Int): List<TVShowModel>
}