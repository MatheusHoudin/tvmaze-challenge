package com.matheus.tvmazechallenge.shared.api

import com.matheus.tvmazechallenge.shared.model.PersonModel
import com.matheus.tvmazechallenge.shared.model.CastCreditsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    @GET("/people")
    suspend fun getPeopleByPage(@Query("page") page: Int): List<PersonModel>

    @GET("/people/{personId}/crewcredits?embed=show")
    suspend fun getPersonCastCredits(@Path("personId") personId: Int): List<CastCreditsModel>
}