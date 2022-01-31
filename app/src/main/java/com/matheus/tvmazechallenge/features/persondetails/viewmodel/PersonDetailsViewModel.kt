package com.matheus.tvmazechallenge.features.persondetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.persondetails.entity.CastCreditsEntity
import com.matheus.tvmazechallenge.features.persondetails.repository.PersonDetailsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import kotlinx.coroutines.launch

class PersonDetailsViewModel(
    private val personDetailsRepository: PersonDetailsRepository
) : ViewModel() {

    private val _castCreditsResult = MutableLiveData<StateData<List<CastCreditsEntity>>>()
    val castCreditsResult: LiveData<StateData<List<CastCreditsEntity>>>
        get() = _castCreditsResult

    fun fetchPersonCastCredit(personId: Int) {
        viewModelScope.launch {
            _castCreditsResult.value = StateData.Loading()
            val castCreditsStateData = personDetailsRepository.getPersonCastCredits(personId)

            if (castCreditsStateData is StateData.Success && castCreditsStateData.data.isEmpty()) {
                _castCreditsResult.value = Failure.thereAreNoCastCreditsFailure
                return@launch
            }

            _castCreditsResult.value = castCreditsStateData
        }
    }
}