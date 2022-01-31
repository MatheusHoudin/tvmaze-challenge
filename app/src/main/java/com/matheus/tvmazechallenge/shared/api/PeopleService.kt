package com.matheus.tvmazechallenge.shared.api

import com.matheus.tvmazechallenge.shared.model.PeopleModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {

    @GET("/people")
    fun getPeopleByPage(@Query("page") page: Int): List<PeopleModel>
}