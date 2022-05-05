package com.matheus.tvmazechallenge.features.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.tvmazechallenge.features.favorites.repository.FavoriteTVShowsRepository
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import com.matheus.tvmazechallenge.shared.extensions.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTVShowsViewModel @Inject constructor(
    private val favoriteTVShowsRepository: FavoriteTVShowsRepository
) : ViewModel() {

    private val _isFavoriteAdded = MutableLiveData<Boolean>()
    val isFavoriteAdded: LiveData<Boolean>
        get() = _isFavoriteAdded

    private val _favoriteEnabled = MutableLiveData<Boolean>()
    val favoriteEnabled: LiveData<Boolean>
        get() = _favoriteEnabled

    private val _favoriteTVShowsResult = MutableLiveData<StateData<List<TVShowEntity>>>()
    val favoriteTVShowsResult: LiveData<StateData<List<TVShowEntity>>>
        get() = _favoriteTVShowsResult

    init {
        fetchFavoriteTVShows()
    }

    fun fetchFavoriteTVShows() = viewModelScope.launch {
        _favoriteTVShowsResult.value = StateData.Loading()
        val favoriteTVShowsStateData = favoriteTVShowsRepository.getAllFavorites()

        if (favoriteTVShowsStateData is StateData.Success && favoriteTVShowsStateData.data.isEmpty()) {
            _favoriteTVShowsResult.value = Failure.thereAreNoFavoriteTVShowsFailure
            return@launch
        }

        _favoriteTVShowsResult.value = favoriteTVShowsStateData
    }

    fun updateFavoriteTVShow(tvShow: TVShowEntity) = viewModelScope.launch {
        val isFavoriteTVShow = favoriteTVShowsRepository.getFavorite(tvShowId = tvShow.id) != null

        if (isFavoriteTVShow)
            removeFavoriteTVShow(tvShow)
        else
            saveFavoriteTVShow(tvShow)
    }

    private fun saveFavoriteTVShow(tvShow: TVShowEntity) = viewModelScope.launch {
        favoriteTVShowsRepository.saveFavorite(tvShow.toModel())
        getFavoriteTVShow(tvShow.id)
        _isFavoriteAdded.value = true
    }

    private fun removeFavoriteTVShow(tvShow: TVShowEntity) = viewModelScope.launch {
        favoriteTVShowsRepository.removeFavorite(tvShow.toModel())
        getFavoriteTVShow(tvShow.id)
        _isFavoriteAdded.value = false
    }

    fun getFavoriteTVShow(tvShowId: Int) = viewModelScope.launch {
        _favoriteEnabled.value = favoriteTVShowsRepository.getFavorite(tvShowId) != null
    }
}