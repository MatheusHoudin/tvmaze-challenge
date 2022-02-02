package com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.repository.TVShowDetailsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class TVShowDetailsViewModelTest : BaseUnitTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<TVShowDetailsRepository>()
    private lateinit var viewModel: TVShowDetailsViewModel

    override fun initialize() {
        viewModel = TVShowDetailsViewModel(repository)
    }

    @Test
    fun `should update tvShowDetailsResult with error when call to getShowEpisodes fails`() {
        val getEpisodesObserver = mockk<Observer<StateData<List<TVShowSeasonEpisodesEntity>>>>()
        val getEpisodesSlot = slot<StateData<List<TVShowSeasonEpisodesEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.noInternetConnectionFailure
        )

        coEvery { repository.getShowEpisodes(any()) } returns Failure.noInternetConnectionFailure
        every { getEpisodesObserver.onChanged(capture(getEpisodesSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == getEpisodesSlot.captured)
            executionCounter++
        }
        viewModel.tvShowDetailsResult.observeForever(getEpisodesObserver)

        viewModel.fetchTVShowEpisodes(1)

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getShowEpisodes(1)
        }
        confirmVerified(repository)
    }

    @Test
    fun `should update tvShowDetailsResult with tv show details and update episodes when call to getShowEpisodes is successful`() {
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
        val getEpisodesObserver = mockk<Observer<StateData<List<TVShowSeasonEpisodesEntity>>>>()
        val getEpisodesSlot = slot<StateData<List<TVShowSeasonEpisodesEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            StateData.Success(expectedTvShowEpisodesEntityList)
        )

        val selectedEpisodesObserver = mockk<Observer<List<TVShowEpisodeEntity>>>()
        val selectedEpisodesSlot = slot<List<TVShowEpisodeEntity>>()
        var episodesExecutionCounter = 0
        val episodesComparisonList = mutableListOf<Boolean>()
        val episodesExpectedExecutions = mutableListOf(
            expectedTvShowEpisodesEntityList.first().episodes
        )

        coEvery { repository.getShowEpisodes(any()) } returns StateData.Success(
            expectedTvShowEpisodesEntityList
        )
        every { getEpisodesObserver.onChanged(capture(getEpisodesSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == getEpisodesSlot.captured)
            executionCounter++
        }
        every { selectedEpisodesObserver.onChanged(capture(selectedEpisodesSlot)) } answers {
            episodesComparisonList.add(episodesExpectedExecutions[episodesExecutionCounter] == selectedEpisodesSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            tvShowDetailsResult.observeForever(getEpisodesObserver)
            selectedEpisodes.observeForever(selectedEpisodesObserver)

            fetchTVShowEpisodes(1)
        }

        Assert.assertEquals(listOf(true), episodesComparisonList)
        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getShowEpisodes(1)
        }
        confirmVerified(repository)
    }
}