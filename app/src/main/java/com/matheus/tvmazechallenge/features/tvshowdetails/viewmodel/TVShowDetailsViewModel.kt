package com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.repository.TVShowDetailsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import kotlinx.coroutines.launch

class TVShowDetailsViewModel(
    private val tvShowDetailsRepository: TVShowDetailsRepository
) : ViewModel() {

    private val _tvShowDetailsResult =
        MutableLiveData<StateData<List<TVShowSeasonEpisodesEntity>>>()
    val tvShowDetailsResult: LiveData<StateData<List<TVShowSeasonEpisodesEntity>>>
        get() = _tvShowDetailsResult

    fun fetchTVShowEpisodes(tvShowId: Int) {
        viewModelScope.launch {
            _tvShowDetailsResult.value = StateData.Loading()
            val tvShowDetailsStateData = tvShowDetailsRepository.getShowEpisodes(tvShowId)

            _tvShowDetailsResult.value = tvShowDetailsStateData
        }
    }
}