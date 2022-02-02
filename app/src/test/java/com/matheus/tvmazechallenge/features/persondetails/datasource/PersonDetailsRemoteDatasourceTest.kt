package com.matheus.tvmazechallenge.features.persondetails.datasource

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.shared.api.PeopleService
import com.matheus.tvmazechallenge.shared.model.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class PersonDetailsRemoteDatasourceTest : BaseUnitTest() {

    private val service = mockk<PeopleService>()
    private lateinit var datasource: PersonDetailsRemoteDatasource

    override fun initialize() {
        datasource = PersonDetailsRemoteDatasourceImpl(service)
    }

    @Test
    fun `should return cast credits model list and use personId from parameters`() {
        val castCreditResponse = listOf(
            CastCreditsModel(
                creditType = "type",
                tvShowCredit = TVShowCreditModel(
                    show = TVShowModel(
                        id = 1,
                        name = "name",
                        url = "url",
                        image = ImageModel(medium = "medium", original = ""),
                        schedule = TVShowScheduleModel("", emptyList()),
                        genres = emptyList(),
                        summary = ""
                    )
                )
            )
        )

        coEvery { service.getPersonCastCredits(any()) } returns castCreditResponse

        val response = runBlocking { datasource.getPersonCastCredits(1) }

        Assert.assertEquals(castCreditResponse, response)
        coVerify(exactly = 1) {
            service.getPersonCastCredits(1)
        }
        confirmVerified(service)
    }
}