package com.matheus.tvmazechallenge.features.persondetails.repository

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.persondetails.datasource.PersonDetailsRemoteDatasource
import com.matheus.tvmazechallenge.features.persondetails.entity.CastCreditsEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.model.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class PersonDetailsRepositoryTest : BaseUnitTest() {

    private val datasource = mockk<PersonDetailsRemoteDatasource>()
    private lateinit var repository: PersonDetailsRepository

    override fun initialize() {
        repository = PersonDetailsRepositoryImpl(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to getPersonCastCredits throws IOException`() {

        coEvery { datasource.getPersonCastCredits(any()) } throws IOException()

        val getPersonCastCreditsResult = runBlocking { repository.getPersonCastCredits(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, getPersonCastCreditsResult)
        coVerify(exactly = 1) {
            datasource.getPersonCastCredits(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to getPersonCastCredits throws SocketException`() {

        coEvery { datasource.getPersonCastCredits(any()) } throws SocketException()

        val getPersonCastCreditsResult = runBlocking { repository.getPersonCastCredits(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, getPersonCastCreditsResult)
        coVerify(exactly = 1) {
            datasource.getPersonCastCredits(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return getCastCreditsFailure when call to getPersonCastCredits throws Exception`() {

        coEvery { datasource.getPersonCastCredits(any()) } throws Exception()

        val getPersonCastCreditsResult = runBlocking { repository.getPersonCastCredits(1) }

        Assert.assertEquals(Failure.getCastCreditsFailure, getPersonCastCreditsResult)
        coVerify(exactly = 1) {
            datasource.getPersonCastCredits(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return CastCreditsEntity list when call to getPersonCastCredits is successful`() {
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
        val castCreditEntityList = listOf(
            CastCreditsEntity(
                creditType = "type",
                tvShow = TVShowEntity(
                    id = 1,
                    name = "name",
                    url = "url",
                    poster = "medium",
                    schedule = TVShowScheduleEntity("", emptyList()),
                    genres = emptyList(),
                    summary = ""
                )
            )
        )
        coEvery { datasource.getPersonCastCredits(any()) } returns castCreditResponse

        val getPersonCastCreditsResult = runBlocking { repository.getPersonCastCredits(1) }

        Assert.assertEquals(StateData.Success(castCreditEntityList), getPersonCastCreditsResult)
        coVerify(exactly = 1) {
            datasource.getPersonCastCredits(1)
        }
        confirmVerified(datasource)
    }
}