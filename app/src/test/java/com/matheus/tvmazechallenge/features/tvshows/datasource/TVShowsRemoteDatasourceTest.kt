package com.matheus.tvmazechallenge.features.tvshows.datasource

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.shared.api.TVShowsService
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

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class TVShowsRemoteDatasourceTest : BaseUnitTest() {

    private val tvShowsService = mockk<TVShowsService>()

    private lateinit var datasource: TVShowsRemoteDatasource

    override fun initialize() {
        datasource = TVShowsRemoteDatasourceImpl(tvShowsService)
    }

    @Test
    fun `should call getShowsByPage with page parameter`() {
        val expectedResponse = listOf(
            TVShowModel(
                id = 1,
                name = "name",
                url = "url",
                image = ImageModel(medium = "medium", original = ""),
                schedule = TVShowScheduleModel("", emptyList()),
                genres = emptyList(),
                summary = ""
            )
        )
        coEvery { tvShowsService.getShowsByPage(any()) } returns expectedResponse

        val tvShowsResponse = runBlocking { datasource.getShowsByPage(1) }

        Assert.assertEquals(expectedResponse, tvShowsResponse)
        coVerify(exactly = 1) {
            tvShowsService.getShowsByPage(1)
        }

        confirmVerified(tvShowsService)
    }
}