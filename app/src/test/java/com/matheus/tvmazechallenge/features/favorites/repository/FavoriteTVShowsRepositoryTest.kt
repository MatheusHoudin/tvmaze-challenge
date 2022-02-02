package com.matheus.tvmazechallenge.features.favorites.repository

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.favorites.datasource.FavoriteTVShowsLocalDatasource
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toEntity
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowScheduleModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class FavoriteTVShowsRepositoryTest : BaseUnitTest() {

    private val datasource = mockk<FavoriteTVShowsLocalDatasource>()
    private lateinit var repository: FavoriteTVShowsRepository

    override fun initialize() {
        repository = FavoriteTVShowsRepositoryImpl(datasource)
    }

    @Test
    fun `should return StateData success when call to saveFavorite is successful`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        coEvery { datasource.saveFavorite(any()) } just Runs

        val result = runBlocking { repository.saveFavorite(tvShowModel) }

        Assert.assertEquals(StateData.Success(Unit), result)
        coVerify(exactly = 1) {
            datasource.saveFavorite(tvShowModel)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return StateData unexpectedFailure when call to saveFavorite fails`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        coEvery { datasource.saveFavorite(any()) } throws Exception()

        val result = runBlocking { repository.saveFavorite(tvShowModel) }

        Assert.assertEquals(Failure.unexpectedFailure, result)
        coVerify(exactly = 1) {
            datasource.saveFavorite(tvShowModel)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return StateData success when call to removeFavorite is successful`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        coEvery { datasource.removeFavorite(any()) } just Runs

        val result = runBlocking { repository.removeFavorite(tvShowModel) }

        Assert.assertEquals(StateData.Success(Unit), result)
        coVerify(exactly = 1) {
            datasource.removeFavorite(tvShowModel)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return StateData unexpectedFailure when call to removeFavorite fails`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        coEvery { datasource.removeFavorite(any()) } throws Exception()

        val result = runBlocking { repository.removeFavorite(tvShowModel) }

        Assert.assertEquals(Failure.unexpectedFailure, result)
        coVerify(exactly = 1) {
            datasource.removeFavorite(tvShowModel)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should get a list of tvShows when call to getAll is successful`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        coEvery { datasource.getAll() } returns listOf(tvShowModel)

        val getAllFavoritesResult = runBlocking { repository.getAllFavorites() }

        Assert.assertEquals(StateData.Success(listOf(tvShowModel.toEntity())), getAllFavoritesResult)
        coVerify(exactly = 1) {
            datasource.getAll()
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should get unexpectedFailure when call to getAll fails`() {
        coEvery { datasource.getAll() } throws Exception()

        val getAllFavoritesResult = runBlocking { repository.getAllFavorites() }

        Assert.assertEquals(Failure.unexpectedFailure, getAllFavoritesResult)
        coVerify(exactly = 1) {
            datasource.getAll()
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return tvShow when call to getFavorite returns tvShow`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        coEvery { datasource.getFavorite(any()) } returns tvShowModel

        val tvShowFavoriteEntity = runBlocking { repository.getFavorite(1) }

        Assert.assertEquals(tvShowModel.toEntity(), tvShowFavoriteEntity)
        coVerify(exactly = 1) {
            datasource.getFavorite(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return null when call to getFavorite returns null`() {
        coEvery { datasource.getFavorite(any()) } returns null

        val tvShowFavoriteEntity = runBlocking { repository.getFavorite(1) }

        Assert.assertNull(tvShowFavoriteEntity)
        coVerify(exactly = 1) {
            datasource.getFavorite(1)
        }
        confirmVerified(datasource)
    }
}