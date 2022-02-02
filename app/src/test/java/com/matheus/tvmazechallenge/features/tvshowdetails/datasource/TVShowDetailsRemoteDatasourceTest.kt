package com.matheus.tvmazechallenge.features.tvshowdetails.datasource

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.shared.api.TVShowsService
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

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class TVShowDetailsRemoteDatasourceTest : BaseUnitTest() {

    private val tvShowService = mockk<TVShowsService>()

    private lateinit var datasource: TVShowDetailsRemoteDatasource

    override fun initialize() {
        datasource = TVShowDetailsRemoteDatasourceImpl(tvShowService)
    }

    @Test
    fun `should call getShowEpisodes with tv show id`() {
        val expectedTvShowEpisodeList = listOf(
            TVShowEpisodeModel(
                name = "name",
                season = 1,
                number = 1,
                summary = "summary",
                image = ImageModel(medium = "", original = null)
            )
        )

        coEvery { tvShowService.getShowEpisodes(any()) } returns expectedTvShowEpisodeList

        val tvShowEpisodeListResponse = runBlocking { datasource.getShowEpisodes(1) }

        Assert.assertEquals(expectedTvShowEpisodeList, tvShowEpisodeListResponse)
        coVerify(exactly = 1) {
            tvShowService.getShowEpisodes(1)
        }
        confirmVerified(tvShowService)
    }
}