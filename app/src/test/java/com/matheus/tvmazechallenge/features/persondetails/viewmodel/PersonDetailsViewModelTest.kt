package com.matheus.tvmazechallenge.features.persondetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.matheus.tvmazechallenge.BaseUnitTest
import com.matheus.tvmazechallenge.features.persondetails.entity.CastCreditsEntity
import com.matheus.tvmazechallenge.features.persondetails.repository.PersonDetailsRepository
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.model.*
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
class PersonDetailsViewModelTest : BaseUnitTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<PersonDetailsRepository>()
    private lateinit var viewModel: PersonDetailsViewModel

    override fun initialize() {
        viewModel = PersonDetailsViewModel(repository)
    }

    @Test
    fun `should return StateData failure when call to getPersonCastCredits fails`() {
        val castCreditsObserver = mockk<Observer<StateData<List<CastCreditsEntity>>>>()
        val castCreditsSlot = slot<StateData<List<CastCreditsEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.noInternetConnectionFailure
        )

        coEvery { repository.getPersonCastCredits(any()) } returns Failure.noInternetConnectionFailure
        every { castCreditsObserver.onChanged(capture(castCreditsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == castCreditsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            castCreditsResult.observeForever(castCreditsObserver)
            fetchPersonCastCredit(1)
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getPersonCastCredits(1)
        }
        confirmVerified(repository)
    }

    @Test
    fun `should return StateData with cast credits entities when call to getPersonCastCredits is successful and it is not an empty list`() {
        val castCreditEntityList = listOf(
            CastCreditsEntity(
                creditType = "type",
                tvShow = TVShowEntity(
                    id = 1,
                    name = "name",
                    url = "url",
                    poster = "medium",
                    schedule = TVShowScheduleEntity("", emptyList()),
                    genres = emptyList(),
                    summary = ""
                )
            )
        )
        val castCreditsObserver = mockk<Observer<StateData<List<CastCreditsEntity>>>>()
        val castCreditsSlot = slot<StateData<List<CastCreditsEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            StateData.Success(castCreditEntityList)
        )

        coEvery { repository.getPersonCastCredits(any()) } returns StateData.Success(castCreditEntityList)
        every { castCreditsObserver.onChanged(capture(castCreditsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == castCreditsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            castCreditsResult.observeForever(castCreditsObserver)
            fetchPersonCastCredit(1)
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getPersonCastCredits(1)
        }
        confirmVerified(repository)
    }

    @Test
    fun `should return thereAreNoCastCreditsFailure failure when call to getPersonCastCredits is successful and it is an empty list`() {
        val castCreditEntityList = emptyList<CastCreditsEntity>()
        val castCreditsObserver = mockk<Observer<StateData<List<CastCreditsEntity>>>>()
        val castCreditsSlot = slot<StateData<List<CastCreditsEntity>>>()
        var executionCounter = 0
        val comparisonList = mutableListOf<Boolean>()
        val expectedExecutions = mutableListOf(
            StateData.Loading(),
            Failure.thereAreNoCastCreditsFailure
        )

        coEvery { repository.getPersonCastCredits(any()) } returns StateData.Success(castCreditEntityList)
        every { castCreditsObserver.onChanged(capture(castCreditsSlot)) } answers {
            comparisonList.add(expectedExecutions[executionCounter] == castCreditsSlot.captured)
            executionCounter++
        }

        with(viewModel) {
            castCreditsResult.observeForever(castCreditsObserver)
            fetchPersonCastCredit(1)
        }

        Assert.assertEquals(listOf(true, true), comparisonList)
        coVerify(exactly = 1) {
            repository.getPersonCastCredits(1)
        }
        confirmVerified(repository)
    }
}