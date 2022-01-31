package com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.repository.TVShowDetailsRepository
import com.matheus.tvmazechallenge.shared.base.StateData
import kotlinx.coroutines.launch

class TVShowDetailsViewModel(
    private val tvShowDetailsRepository: TVShowDetailsRepository
) : ViewModel() {

    private var selectedSeasonIndex = INITIAL_SEASON_INDEX

    private val _tvShowDetailsResult =
        MutableLiveData<StateData<List<TVShowSeasonEpisodesEntity>>>()
    val tvShowDetailsResult: LiveData<StateData<List<TVShowSeasonEpisodesEntity>>>
        get() = _tvShowDetailsResult

    private val _selectedEpisodes = MutableLiveData<List<TVShowEpisodeEntity>>()
    val selectedEpisodes: LiveData<List<TVShowEpisodeEntity>>
        get() = _selectedEpisodes

    fun fetchTVShowEpisodes(tvShowId: Int) {
        viewModelScope.launch {
            _tvShowDetailsResult.value = StateData.Loading()
            val tvShowDetailsStateData = tvShowDetailsRepository.getShowEpisodes(tvShowId)

            _tvShowDetailsResult.value = tvShowDetailsStateData

            setSelectedSeasonIndex(INITIAL_SEASON_INDEX)
        }
    }

    fun setSelectedSeasonIndex(seasonIndex: Int) {
        selectedSeasonIndex = seasonIndex
        if (_tvShowDetailsResult.value is StateData.Success) {
            _selectedEpisodes.value =
                (_tvShowDetailsResult.value as StateData.Success).data[selectedSeasonIndex].episodes
        }
    }

    private companion object {
        const val INITIAL_SEASON_INDEX = 0
    }
}