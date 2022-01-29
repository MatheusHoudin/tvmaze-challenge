package com.matheus.tvmazechallenge.features.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepository
import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import kotlinx.coroutines.launch

class SearchTVShowsViewModel(
    private val searchTVShowRepository: SearchTVShowRepository
) : ViewModel() {

    private val _showsResult = MutableLiveData<StateData<List<TVShowEntity>>>()
    val showsResult: LiveData<StateData<List<TVShowEntity>>>
        get() = _showsResult

    fun searchShows(search: String) {
        viewModelScope.launch {
            _showsResult.value = StateData.Loading()
            val foundShowsStateData = searchTVShowRepository.searchShows(search)

            if (foundShowsStateData is StateData.Success && foundShowsStateData.data.isEmpty()) {
                _showsResult.value = Failure.notFoundShowsFailure
                return@launch
            }

            _showsResult.value = foundShowsStateData
        }
    }
}