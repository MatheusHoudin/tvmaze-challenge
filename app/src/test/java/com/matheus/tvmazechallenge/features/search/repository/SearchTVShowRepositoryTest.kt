package com.matheus.tvmazechallenge.features.search.repository

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.SearchTVShowModel
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
class SearchTVShowRepositoryTest : BaseUnitTest() {

    private val datasource = mockk<SearchTVShowRemoteDatasource>()
    private lateinit var repository: SearchTVShowRepository

    override fun initialize() {
        repository = SearchTVShowRepositoryImpl(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to searchShows fails with IOException`() {
        coEvery { datasource.searchShows(any()) } throws IOException()

        val searchTVShowsResult = runBlocking { repository.searchShows("search") }

        Assert.assertEquals(Failure.noInternetConnectionFailure, searchTVShowsResult)
        coVerify(exactly = 1) {
            datasource.searchShows("search")
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to searchShows fails with SocketException`() {
        coEvery { datasource.searchShows(any()) } throws SocketException()

        val searchTVShowsResult = runBlocking { repository.searchShows("search") }

        Assert.assertEquals(Failure.noInternetConnectionFailure, searchTVShowsResult)
        coVerify(exactly = 1) {
            datasource.searchShows("search")
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return unexpectedFailure when call to searchShows fails with Exception`() {
        coEvery { datasource.searchShows(any()) } throws Exception()

        val searchTVShowsResult = runBlocking { repository.searchShows("search") }

        Assert.assertEquals(Failure.unexpectedFailure, searchTVShowsResult)
        coVerify(exactly = 1) {
            datasource.searchShows("search")
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return StateData Success with TvShowEntity list when call to searchShows is successful`() {
        val searchTvShowsResponse = listOf(
            SearchTVShowModel(
                score = 1.0,
                show = TVShowModel(
                    id = 1,
                    name = "name",
                    url = "url",
                    image = ImageModel(medium = "medium", original = "original"),
                    schedule = TVShowScheduleModel("time", listOf("day")),
                    genres = listOf("genre"),
                    summary = "summary"
                )
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

        coEvery { datasource.searchShows(any()) } returns searchTvShowsResponse

        val searchTVShowsResult = runBlocking { repository.searchShows("search") }

        Assert.assertEquals(StateData.Success(tvShowsEntities), searchTVShowsResult)
        coVerify(exactly = 1) {
            datasource.searchShows("search")
        }
        confirmVerified(datasource)
    }
}