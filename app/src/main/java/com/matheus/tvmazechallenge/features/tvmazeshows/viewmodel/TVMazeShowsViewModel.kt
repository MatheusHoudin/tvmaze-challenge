package com.matheus.tvmazechallenge.features.tvmazeshows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.tvmazeshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvmazeshows.repository.TVMazeShowsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import kotlinx.coroutines.launch

class TVMazeShowsViewModel(
    private val tvMazeShowsRepository: TVMazeShowsRepository
) : ViewModel() {

    private val tvMazeShows = mutableListOf<TVShowEntity>()
    private var currentPage = INITIAL_PAGE

    private val _showsResult = MutableLiveData<StateData<List<TVShowEntity>>>()
    val showsResult: LiveData<StateData<List<TVShowEntity>>>
        get() = _showsResult

    fun fetchShows() {
        viewModelScope.launch {
            _showsResult.value = StateData.Loading()
            val showsStateData = tvMazeShowsRepository.getShowsByPage(currentPage)

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