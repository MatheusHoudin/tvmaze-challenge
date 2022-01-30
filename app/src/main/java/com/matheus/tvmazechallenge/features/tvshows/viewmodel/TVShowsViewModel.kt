package com.matheus.tvmazechallenge.features.tvshows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import kotlinx.coroutines.launch

class TVShowsViewModel(
    private val tvShowsRepository: TVShowsRepository
) : ViewModel() {

    private val tvMazeShows = mutableListOf<TVShowEntity>()
    private var currentPage = INITIAL_PAGE

    private val _showsResult = MutableLiveData<StateData<List<TVShowEntity>>>()
    val showsResult: LiveData<StateData<List<TVShowEntity>>>
        get() = _showsResult

    fun fetchShows() {
        viewModelScope.launch {
            _showsResult.value = StateData.Loading()
            val showsStateData = tvShowsRepository.getShowsByPage(currentPage)

            if (showsStateData is StateData.Success) {
                updateTvShows(showsStateData.data)
                return@launch
            }

            _showsResult.value = showsStateData
        }
    }

    private fun updateTvShows(tvShowEntities: List<TVShowEntity>) {
        tvMazeShows.addAll(tvShowEntities)
        _showsResult.value = StateData.Success(tvMazeShows)
        currentPage++
    }

    private companion object {
        const val INITIAL_PAGE = 1
    }
}