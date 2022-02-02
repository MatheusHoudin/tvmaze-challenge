package com.matheus.tvmazechallenge.features.people.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.features.people.repository.PeopleRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import kotlinx.coroutines.launch

class PeopleViewModel(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private val people = mutableListOf<PersonEntity>()
    private var currentPage = INITIAL_PAGE

    private val _peopleResult = MutableLiveData<StateData<List<PersonEntity>>>()
    val peopleResult: LiveData<StateData<List<PersonEntity>>>
        get() = _peopleResult

    fun fetchPeople() {
        viewModelScope.launch {
            _peopleResult.value = StateData.Loading()
            val peopleStateData = peopleRepository.getPeopleByPage(currentPage)

            if (peopleStateData is StateData.Success) {
                updatePeople(peopleStateData.data)
                return@launch
            }

            _peopleResult.value = peopleStateData
        }
    }

    private fun updatePeople(people: List<PersonEntity>) {
        this.people.addAll(people)
        _peopleResult.value = StateData.Success(this.people)
        currentPage++
    }

    private companion object {
        const val INITIAL_PAGE = 1
    }
}