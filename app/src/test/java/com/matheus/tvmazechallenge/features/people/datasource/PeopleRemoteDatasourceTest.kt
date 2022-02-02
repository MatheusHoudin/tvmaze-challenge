package com.matheus.tvmazechallenge.features.people.datasource

import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.shared.api.PeopleService
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.PersonModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class PeopleRemoteDatasourceTest : BaseUnitTest() {

    private val service = mockk<PeopleService>()
    private lateinit var datasource: PeopleRemoteDatasource

    override fun initialize() {
        datasource = PeopleRemoteDatasourceImpl(service)
    }

    @Test
    fun `should return list of PeopleModel with page from parameter`() {
        val peopleModelList = listOf(
            PersonModel(
                id = 1,
                name = "name",
                url = "url",
                image = ImageModel(medium = "medium", original = null)
            )
        )

        coEvery { service.getPeopleByPage(any()) } returns peopleModelList

        val peopleResult = runBlocking { datasource.getPeopleByPage(1) }

        Assert.assertEquals(peopleModelList, peopleResult)
        coVerify(exactly = 1) {
            service.getPeopleByPage(1)
        }
        confirmVerified(service)
    }
}