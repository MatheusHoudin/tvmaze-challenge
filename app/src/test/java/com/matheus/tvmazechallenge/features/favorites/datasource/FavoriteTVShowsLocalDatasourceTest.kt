package com.matheus.tvmazechallenge.features.favorites.datasource

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.shared.dao.TVShowDatabaseDAO
import com.matheus.tvmazechallenge.shared.extensions.toDAO
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowScheduleModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class FavoriteTVShowsLocalDatasourceTest : BaseUnitTest() {

    private val database = mockk<TVShowDatabaseDAO>()
    private lateinit var datasource: FavoriteTVShowsLocalDatasource

    override fun initialize() {
        datasource = FavoriteTVShowsLocalDatasourceImpl(database)
    }

    @Test
    fun `should save favorite from parameter`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )
        coEvery { database.saveFavorite(any()) } just Runs

        runBlocking { datasource.saveFavorite(tvShowModel) }

        coVerify(exactly = 1) {
            database.saveFavorite(tvShowModel.toDAO())
        }
        confirmVerified(database)
    }

    @Test
    fun `should remove favorite from parameter`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = "medium"),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )
        coEvery { database.removeFavorite(any()) } just Runs

        runBlocking { datasource.removeFavorite(tvShowModel) }

        coVerify(exactly = 1) {
            database.removeFavorite(tvShowModel.toDAO())
        }
        confirmVerified(database)
    }

    @Test
    fun `should get all tv show`() {
        val tvShowModelList = listOf(
            TVShowModel(
                id = 1,
                name = "name",
                url = "url",
                image = ImageModel(medium = "medium", original = "medium"),
                schedule = TVShowScheduleModel("", emptyList()),
                genres = emptyList(),
                summary = ""
            )
        )

        coEvery { database.getAll() } returns tvShowModelList.map { it.toDAO() }

        val tvShowModelListResult = runBlocking { datasource.getAll() }

        Assert.assertEquals(tvShowModelList, tvShowModelListResult)
        coVerify(exactly = 1) {
            database.getAll()
        }
        confirmVerified(database)
    }

    @Test
    fun `should return null when tv show is not on favorite list`() {
        coEvery { database.getFavorite(any()) } returns null

        val tvShowModel = runBlocking { datasource.getFavorite(1) }

        Assert.assertNull(tvShowModel)
        coVerify(exactly = 1) {
            database.getFavorite(1)
        }
        confirmVerified(database)
    }

    @Test
    fun `should return tv show when tv show is on favorite list`() {
        val tvShowModel = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = "medium"),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        coEvery { database.getFavorite(any()) } returns tvShowModel.toDAO()

        val tvShowModelResult = runBlocking { datasource.getFavorite(1) }

        Assert.assertEquals(tvShowModel, tvShowModelResult)
        coVerify(exactly = 1) {
            database.getFavorite(1)
        }
        confirmVerified(database)
    }
}