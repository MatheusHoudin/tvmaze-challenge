package com.matheus.tvmazechallenge.features.people.repository

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.people.datasource.PeopleRemoteDatasource
import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.model.*
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
class PeopleRepositoryTest : BaseUnitTest() {

    private val datasource = mockk<PeopleRemoteDatasource>()
    private lateinit var repository: PeopleRepository

    override fun initialize() {
        repository = PeopleRepositoryImpl(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to getPeopleByPage throws IOException`() {

        coEvery { datasource.getPeopleByPage(any()) } throws IOException()

        val getPeopleResult = runBlocking { repository.getPeopleByPage(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, getPeopleResult)
        coVerify(exactly = 1) {
            datasource.getPeopleByPage(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return noInternetConnectionFailure when call to getPeopleByPage throws SocketException`() {

        coEvery { datasource.getPeopleByPage(any()) } throws SocketException()

        val getPeopleResult = runBlocking { repository.getPeopleByPage(1) }

        Assert.assertEquals(Failure.noInternetConnectionFailure, getPeopleResult)
        coVerify(exactly = 1) {
            datasource.getPeopleByPage(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return getPeopleFailure when call to getPeopleByPage throws Exception`() {

        coEvery { datasource.getPeopleByPage(any()) } throws Exception()

        val getPeopleResult = runBlocking { repository.getPeopleByPage(1) }

        Assert.assertEquals(Failure.getPeopleFailure, getPeopleResult)
        coVerify(exactly = 1) {
            datasource.getPeopleByPage(1)
        }
        confirmVerified(datasource)
    }

    @Test
    fun `should return PersonEntity list when call to getPeopleByPage is successful`() {
        val peopleResponse = listOf(
            PersonModel(
                id = 1,
                name = "name",
                url = "url",
                image = ImageModel(medium = "medium", original = null)
            )
        )
        val peopleEntityList = listOf(
            PersonEntity(
                id = 1,
                name = "name",
                url = "url",
                image = "medium"
            )
        )
        coEvery { datasource.getPeopleByPage(any()) } returns peopleResponse

        val getPeopleResult = runBlocking { repository.getPeopleByPage(1) }

        Assert.assertEquals(StateData.Success(peopleEntityList), getPeopleResult)
        coVerify(exactly = 1) {
            datasource.getPeopleByPage(1)
        }
        confirmVerified(datasource)
    }
}