package com.matheus.tvmazechallenge.shared.api

import com.matheus.tvmazechallenge.shared.model.PeopleModel
import com.matheus.tvmazechallenge.shared.model.PersonDetailsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {

    @GET("/people")
    suspend fun getPeopleByPage(@Query("page") page: Int): List<PeopleModel>

    @GET("/{personId}/castcredits?embed=show")
    suspend fun getPersonCastCredits(personId: Int): List<PersonDetailsModel>
}