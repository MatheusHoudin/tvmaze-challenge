package com.matheus.tvmazechallenge.features.tvshows.repository

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.tvshows.datasource.TVShowsRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowScheduleModel
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
class TVShowRepositoryTest : BaseUnitTest() {

    private val remoteDatasource = mockk<TVShowsRemoteDatasource>()

    private lateinit var repository: TVShowsRepository

    override fun initialize() {
        repository = TVShowsRepositoryImpl(remoteDatasource)
    }

    @Test
    fun `should return success with TVShowEntity list when successfully calling datasource`() {
        val tvShowsResponse = listOf(
            TVShowModel(
                id = 1,
                name = "name",
                url = "url",
                image = ImageModel(medium = "medium", original = "original"),
                schedule = TVShowScheduleModel("time", listOf("day")),
                genres = listOf("genre"),
                summary = "summary"
            )
        )
        val tvShowsEntities = listOf(
            TVShowEntity(
                id = 1,
                name = "name",
                url = "url",
                poster = "medium",
                schedule = TVShowScheduleEntity("time", listOf("day")),
                genres = listOf("genre"),
                summary = "summary"
            )
        )

        coEvery { remoteDatasource.getShowsByPage(any()) } returns tvShowsResponse

        val stateDataResponse = runBlocking { repository.getShowsByPage(1) }

        Assert.assertEquals(StateData.Success(tvShowsEntities), stateDataResponse)
        coVerify(exactly = 1) {
            remoteDatasource.getShowsByPage(1)
        }

        confirmVerified(remoteDatasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to datasource throws IOException`() {
        coEvery { remoteDatasource.getShowsByPage(any()) } throws IOException()

        val stateDataResponse = runBlocking { repository.getShowsByPage(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, stateDataResponse)
        coVerify(exactly = 1) {
            remoteDatasource.getShowsByPage(1)
        }

        confirmVerified(remoteDatasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to datasource throws SocketException`() {
        coEvery { remoteDatasource.getShowsByPage(any()) } throws SocketException()

        val stateDataResponse = runBlocking { repository.getShowsByPage(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, stateDataResponse)
        coVerify(exactly = 1) {
            remoteDatasource.getShowsByPage(1)
        }

        confirmVerified(remoteDatasource)
    }

    @Test
    fun `should return unexpectedFailure when call to datasource throws a generic exception`() {
        coEvery { remoteDatasource.getShowsByPage(any()) } throws Exception()

        val stateDataResponse = runBlocking { repository.getShowsByPage(1) }

        Assert.assertEquals(Failure.unexpectedFailure, stateDataResponse)
        coVerify(exactly = 1) {
            remoteDatasource.getShowsByPage(1)
        }

        confirmVerified(remoteDatasource)
    }
}