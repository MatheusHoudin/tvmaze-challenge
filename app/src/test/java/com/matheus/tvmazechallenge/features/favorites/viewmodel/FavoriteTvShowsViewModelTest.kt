package com.matheus.tvmazechallenge.features.favorites.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.favorites.repository.FavoriteTVShowsRepository
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toModel
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowScheduleModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class FavoriteTvShowsViewModelTest : BaseUnitTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<FavoriteTVShowsRepository>()
    private lateinit var viewModel: FavoriteTVShowsViewModel

    override fun initialize() {
        viewModel = FavoriteTVShowsViewModel(repository)
    }

    @Test
    fun `should return StateData failure when call to getAllFavorites fails`() {
        val favoriteTvShowsObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val favoriteTvShowsSlot = slot<StateData<List<TVShowEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.noInternetConnectionFailure
        )

        coEvery { repository.getAllFavorites() } returns Failure.noInternetConnectionFailure
        every { favoriteTvShowsObserver.onChanged(capture(favoriteTvShowsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == favoriteTvShowsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            favoriteTVShowsResult.observeForever(favoriteTvShowsObserver)
            fetchFavoriteTVShows()
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getAllFavorites()
        }
        confirmVerified(repository)
    }

    @Test
    fun `should return StateData with favorite tv show entities when call to getAllFavorites is successful and it is not an empty list`() {
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
        val favoriteTvShowsObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val favoriteTvShowsSlot = slot<StateData<List<TVShowEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            StateData.Success(tvShowEntitiesResponse)
        )

        coEvery { repository.getAllFavorites() } returns StateData.Success(tvShowEntitiesResponse)
        every { favoriteTvShowsObserver.onChanged(capture(favoriteTvShowsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == favoriteTvShowsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            favoriteTVShowsResult.observeForever(favoriteTvShowsObserver)
            fetchFavoriteTVShows()
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getAllFavorites()
        }
        confirmVerified(repository)
    }

    @Test
    fun `should return thereAreNoFavoriteTVShowsFailure when call to getAllFavorites is successful and it is an empty list`() {
        val favoriteTvShowEntitiesResponse = emptyList<TVShowEntity>()
        val favoriteTvShowsObserver = mockk<Observer<StateData<List<TVShowEntity>>>>()
        val favoriteTvShowsSlot = slot<StateData<List<TVShowEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.thereAreNoFavoriteTVShowsFailure
        )

        coEvery { repository.getAllFavorites() } returns StateData.Success(
            favoriteTvShowEntitiesResponse
        )
        every { favoriteTvShowsObserver.onChanged(capture(favoriteTvShowsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == favoriteTvShowsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            favoriteTVShowsResult.observeForever(favoriteTvShowsObserver)
            fetchFavoriteTVShows()
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getAllFavorites()
        }
        confirmVerified(repository)
    }

    @Test
    fun `should save favorite tv show when getFavorite returns null`() {
        val isFavoriteAddedObserver = mockk<Observer<Boolean>>()
        val isFavoriteAddedSlot = slot<Boolean>()
        var isFavoriteAdded = false
        val tvShowEntity = TVShowEntity(
            id = 1,
            name = "name",
            url = "url",
            poster = "medium",
            schedule = TVShowScheduleEntity("time", listOf("day")),
            genres = listOf("genre"),
            summary = "summary"
        )

        every { isFavoriteAddedObserver.onChanged(capture(isFavoriteAddedSlot)) } answers {
            isFavoriteAdded = isFavoriteAddedSlot.captured
        }
        coEvery { repository.getFavorite(any()) } returns null
        coEvery { repository.saveFavorite(any()) } returns StateData.Success(Unit)

        viewModel.isFavoriteAdded.observeForever(isFavoriteAddedObserver)

        runBlocking { viewModel.updateFavoriteTVShow(tvShowEntity) }

        Assert.assertTrue(isFavoriteAdded)
        coVerify(exactly = 1) {
            repository.saveFavorite(tvShowEntity.toModel())
        }
        coVerify(exactly = 2) {
            repository.getFavorite(1)
        }
        confirmVerified(repository)
    }

    @Test
    fun `should remove favorite tv show when getFavorite returns non null data`() {
        val isFavoriteAddedObserver = mockk<Observer<Boolean>>()
        val isFavoriteAddedSlot = slot<Boolean>()
        var isFavoriteAdded = true
        val tvShowEntity = TVShowEntity(
            id = 1,
            name = "name",
            url = "url",
            poster = "medium",
            schedule = TVShowScheduleEntity("time", listOf("day")),
            genres = listOf("genre"),
            summary = "summary"
        )

        every { isFavoriteAddedObserver.onChanged(capture(isFavoriteAddedSlot)) } answers {
            isFavoriteAdded = isFavoriteAddedSlot.captured
        }
        coEvery { repository.getFavorite(any()) } returns tvShowEntity
        coEvery { repository.removeFavorite(any()) } returns StateData.Success(Unit)

        viewModel.isFavoriteAdded.observeForever(isFavoriteAddedObserver)

        runBlocking { viewModel.updateFavoriteTVShow(tvShowEntity) }

        Assert.assertFalse(isFavoriteAdded)
        coVerify(exactly = 1) {
            repository.removeFavorite(tvShowEntity.toModel())
        }
        coVerify(exactly = 2) {
            repository.getFavorite(1)
        }
        confirmVerified(repository)
    }

    @Test
    fun `should set true to favoriteEnabled when call to getFavorite returns a tv show`() {
        val isFavoriteEnabledObserver = mockk<Observer<Boolean>>()
        val isFavoriteEnabledSlot = slot<Boolean>()
        var isFavoriteEnabled = false
        val tvShowEntity = TVShowEntity(
            id = 1,
            name = "name",
            url = "url",
            poster = "medium",
            schedule = TVShowScheduleEntity("time", listOf("day")),
            genres = listOf("genre"),
            summary = "summary"
        )

        every { isFavoriteEnabledObserver.onChanged(capture(isFavoriteEnabledSlot)) } answers {
            isFavoriteEnabled = isFavoriteEnabledSlot.captured
        }
        coEvery { repository.getFavorite(any()) } returns tvShowEntity

        viewModel.favoriteEnabled.observeForever(isFavoriteEnabledObserver)

        runBlocking { viewModel.getFavoriteTVShow(1) }

        Assert.assertTrue(isFavoriteEnabled)
        coVerify(exactly = 1) {
            repository.getFavorite(1)
        }
        confirmVerified(repository)
    }

    @Test
    fun `should set false to favoriteEnabled when call to getFavorite returns null`() {
        val isFavoriteEnabledObserver = mockk<Observer<Boolean>>()
        val isFavoriteEnabledSlot = slot<Boolean>()
        var isFavoriteEnabled = true
        every { isFavoriteEnabledObserver.onChanged(capture(isFavoriteEnabledSlot)) } answers {
            isFavoriteEnabled = isFavoriteEnabledSlot.captured
        }
        coEvery { repository.getFavorite(any()) } returns null

        viewModel.favoriteEnabled.observeForever(isFavoriteEnabledObserver)

        runBlocking { viewModel.getFavoriteTVShow(1) }

        Assert.assertFalse(isFavoriteEnabled)
        coVerify(exactly = 1) {
            repository.getFavorite(1)
        }
        confirmVerified(repository)
    }
}