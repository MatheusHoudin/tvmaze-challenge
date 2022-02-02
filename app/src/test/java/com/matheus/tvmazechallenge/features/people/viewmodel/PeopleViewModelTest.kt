package com.matheus.tvmazechallenge.features.people.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.features.people.repository.PeopleRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class PeopleViewModelTest : BaseUnitTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<PeopleRepository>()
    private lateinit var viewModel: PeopleViewModel

    override fun initialize() {
        viewModel = PeopleViewModel(repository)
    }

    @Test
    fun `should update peopleResult live data and not save peopleResult when call to repository fails`() {
        val peopleResultObserver = mockk<Observer<StateData<List<PersonEntity>>>>()
        val peopleResultSlot = slot<StateData<List<PersonEntity>>>()
        val executions = mutableListOf<StateData<List<PersonEntity>>>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.noInternetConnectionFailure
        )

        coEvery { repository.getPeopleByPage(any()) } returns Failure.noInternetConnectionFailure
        every { peopleResultObserver.onChanged(capture(peopleResultSlot)) } answers {
            executions.add(peopleResultSlot.captured)
        }

        with(viewModel) {
            peopleResult.observeForever(peopleResultObserver)
            fetchPeople()
        }

        Assert.assertEquals(expectedExecutions, executions)
        coVerify(exactly = 1) {
            repository.getPeopleByPage(1)
        }

        confirmVerified(repository)
    }

    @Test
    fun `should save every person page when call to repository is successful`() = runBlockingTest {
        val personEntity = PersonEntity(
            id = 1,
            name = "name",
            url = "url",
            image = "medium"
        )

        val peopleResultObserver = mockk<Observer<StateData<List<PersonEntity>>>>()
        val peopleResultSlot = slot<StateData<List<PersonEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            StateData.Success(listOf(personEntity)),
            StateData.Loading(),
            StateData.Success(listOf(personEntity, personEntity))
        )

        coEvery { repository.getPeopleByPage(any()) } returns StateData.Success(listOf(personEntity))
        every { peopleResultObserver.onChanged(capture(peopleResultSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == peopleResultSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            peopleResult.observeForever(peopleResultObserver)
            fetchPeople()
            fetchPeople()
        }

        Assert.assertEquals(listOf(true, true, true, true), comparisonList)
        coVerify {
            repository.getPeopleByPage(1)
            repository.getPeopleByPage(2)
        }
        confirmVerified(repository)
    }
}