package com.matheus.tvmazechallenge.features.search.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepository
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class SearchTVModelsViewModelTest : BaseUnitTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<SearchTVShowRepository>()
    private lateinit var viewModel: SearchTVShowsViewModel

    override fun initialize() {
        viewModel = SearchTVShowsViewModel(repository)
    }

    @Test
    fun `should return StateData failure when call to searchShows fails`() {
        val tvShowsObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val tvShowsSlot = slot<StateData<List<TVShowEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.noInternetConnectionFailure
        )

        coEvery { repository.searchShows(any()) } returns Failure.noInternetConnectionFailure
        every { tvShowsObserver.onChanged(capture(tvShowsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == tvShowsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            showsResult.observeForever(tvShowsObserver)
            searchShows("search")
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.searchShows("search")
        }
        confirmVerified(repository)
    }

    @Test
    fun `should return StateData with tv show entities when call to searchShows is successful and it is not an empty list`() {
        val tvShowEntitiesResponse = listOf(
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
        val tvShowsObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val tvShowsSlot = slot<StateData<List<TVShowEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            StateData.Success(tvShowEntitiesResponse)
        )

        coEvery { repository.searchShows(any()) } returns StateData.Success(tvShowEntitiesResponse)
        every { tvShowsObserver.onChanged(capture(tvShowsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == tvShowsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            showsResult.observeForever(tvShowsObserver)
            searchShows("search")
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.searchShows("search")
        }
        confirmVerified(repository)
    }

    @Test
    fun `should return not found shows failure when call to searchShows is successful and it is an empty list`() {
        val tvShowEntitiesResponse = emptyList<TVShowEntity>()
        val tvShowsObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val tvShowsSlot = slot<StateData<List<TVShowEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.notFoundShowsFailure
        )

        coEvery { repository.searchShows(any()) } returns StateData.Success(tvShowEntitiesResponse)
        every { tvShowsObserver.onChanged(capture(tvShowsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == tvShowsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            showsResult.observeForever(tvShowsObserver)
            searchShows("search")
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.searchShows("search")
        }
        confirmVerified(repository)
    }
}