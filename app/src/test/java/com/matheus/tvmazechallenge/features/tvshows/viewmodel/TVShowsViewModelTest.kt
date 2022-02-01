package com.matheus.tvmazechallenge.features.tvshows.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class TVShowsViewModelTest : BaseUnitTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<TVShowsRepository>()
    private lateinit var viewModel: TVShowsViewModel

    override fun initialize() {
        viewModel = TVShowsViewModel(repository)
    }

    @Test
    fun `should update showsResult live data and not save showsResult when call to repository fails`() {
        val showsResultObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val showsResultSlot = slot<StateData<List<TVShowEntity>>>()
        val executions = mutableListOf<StateData<List<TVShowEntity>>>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.noInternetConnectionFailure
        )

        coEvery { repository.getShowsByPage(any()) } returns Failure.noInternetConnectionFailure
        every { showsResultObserver.onChanged(capture(showsResultSlot)) } answers {
            executions.add(showsResultSlot.captured)
        }
        viewModel.showsResult.observeForever(showsResultObserver)

        viewModel.fetchShows()

        Assert.assertEquals(expectedExecutions, executions)
        coVerify(exactly = 1) {
            repository.getShowsByPage(1)
        }

        confirmVerified(repository)
    }

    @Test
    fun `should save every tvShow page when call to repository is successful`() = runBlockingTest {
        val tvShowEntity = TVShowEntity(
            id = 1,
            name = "name",
            url = "url",
            poster = "medium",
            schedule = TVShowScheduleEntity("time", listOf("day")),
            genres = listOf("genre"),
            summary = "summary"
        )
        val showsResultObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val showsResultSlot = slot<StateData<List<TVShowEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            StateData.Success(listOf(tvShowEntity)),
            StateData.Loading(),
            StateData.Success(listOf(tvShowEntity, tvShowEntity))
        )

        coEvery { repository.getShowsByPage(any()) } returns StateData.Success(listOf(tvShowEntity))
        every { showsResultObserver.onChanged(capture(showsResultSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == showsResultSlot.captured)
            executionCounter++
        }
        viewModel.showsResult.observeForever(showsResultObserver)

        viewModel.fetchShows()
        viewModel.fetchShows()

        Assert.assertEquals(listOf(true, true, true, true), comparisonList)
        coVerify {
            repository.getShowsByPage(1)
            repository.getShowsByPage(2)
        }
        confirmVerified(repository)
    }
}