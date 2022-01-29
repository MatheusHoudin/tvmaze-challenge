package com.matheus.tvmazechallenge.features.tvmazeshows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvmazeshows.repository.shows.TVMazeShowsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import kotlinx.coroutines.launch

class TVMazeShowsViewModel(
    private val tvMazeShowsRepository: TVMazeShowsRepository
) : ViewModel() {

    private var showsPage = INITIAL_PAGE

    private val _showsResult = MutableLiveData<StateData<List<TVShowEntity>>>()
    val showsResult: LiveData<StateData<List<TVShowEntity>>>
        get() = _showsResult

    fun fetchShows() {
        viewModelScope.launch {
            _showsResult.value = StateData.Loading()
            _showsResult.value = tvMazeShowsRepository.getShowsByPage(showsPage)
        }
    }

    private companion object {
        const val INITIAL_PAGE = 1
    }
}