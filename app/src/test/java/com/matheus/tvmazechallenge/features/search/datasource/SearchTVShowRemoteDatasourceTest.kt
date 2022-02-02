package com.matheus.tvmazechallenge.features.search.datasource

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowRemoteDatasource
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowRemoteDatasourceImpl
import com.matheus.tvmazechallenge.shared.api.TVShowsService
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

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class SearchTVShowRemoteDatasourceTest : BaseUnitTest() {

    private val service = mockk<TVShowsService>()
    private lateinit var datasource: SearchTVShowRemoteDatasource

    override fun initialize() {
        datasource = SearchTVShowRemoteDatasourceImpl(service)
    }

    @Test
    fun `should call searchShows with search from parameters`() {
        val searchTVShowModelList = listOf(
            SearchTVShowModel(
                score = 1.0,
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
        coEvery { service.searchShows(any()) } returns searchTVShowModelList

        val searchTvShowResult = runBlocking { datasource.searchShows("search") }

        Assert.assertEquals(searchTVShowModelList, searchTvShowResult)
        coVerify(exactly = 1) {
            service.searchShows("search")
        }
        confirmVerified(service)
    }
}