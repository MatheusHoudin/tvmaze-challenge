package com.matheus.tvmazechallenge.features.tvshowdetails.repository

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.tvshowdetails.datasource.TVShowDetailsRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowEpisodeModel
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
class TVShowDetailsRepositoryTest : BaseUnitTest() {

    private val datasource = mockk<TVShowDetailsRemoteDatasource>()

    private lateinit var repository: TVShowDetailsRepository

    override fun initialize() {
        repository = TVShowDetailsRepositoryImpl(datasource)
    }

    @Test
    fun `should return tv show episodes with StateData Success when call to datasource is successful`() {
        val tvShowEpisodesModelList = listOf(
            TVShowEpisodeModel(
                name = "name",
                season = 1,
                number = 1,
                summary = "summary",
                image = ImageModel(medium = "medium", original = null)
            ),
            TVShowEpisodeModel(
                name = "name2",
                season = 1,
                number = 2,
                summary = "summary2",
                image = ImageModel(medium = "medium2", original = null)
            )
        )
        val expectedTvShowEpisodesEntityList = listOf(
            TVShowSeasonEpisodesEntity(
                season = "1",
                episodes = mutableListOf(
                    TVShowEpisodeEntity(
                        name = "name",
                        season = "1",
                        number = "1",
                        summary = "summary",
                        image = "medium"
                    ),
                    TVShowEpisodeEntity(
                        name = "name2",
                        season = "1",
                        number = "2",
                        summary = "summary2",
                        image = "medium2"
                    )
                )
            )
        )

        coEvery { datasource.getShowEpisodes(any()) } returns tvShowEpisodesModelList

        val showEpisodesResponse = runBlocking { repository.getShowEpisodes(1) }

        Assert.assertEquals(
            StateData.Success(expectedTvShowEpisodesEntityList),
            showEpisodesResponse
        )
        coVerify(exactly = 1) {
            datasource.getShowEpisodes(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to getShowEpisodes throws IOException`() {
        coEvery { datasource.getShowEpisodes(any()) } throws IOException()

        val getShowEpisodesResponse = runBlocking { repository.getShowEpisodes(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, getShowEpisodesResponse)
        coVerify(exactly = 1) {
            datasource.getShowEpisodes(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to getShowEpisodes throws SocketException`() {
        coEvery { datasource.getShowEpisodes(any()) } throws SocketException()

        val getShowEpisodesResponse = runBlocking { repository.getShowEpisodes(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, getShowEpisodesResponse)
        coVerify(exactly = 1) {
            datasource.getShowEpisodes(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return getEpisodesFailure when call to getShowEpisodes throws Exception`() {
        coEvery { datasource.getShowEpisodes(any()) } throws Exception()

        val getShowEpisodesResponse = runBlocking { repository.getShowEpisodes(1) }

        Assert.assertEquals(Failure.getEpisodesFailure, getShowEpisodesResponse)
        coVerify(exactly = 1) {
            datasource.getShowEpisodes(1)
        }
        confirmVerified(datasource)
    }
}