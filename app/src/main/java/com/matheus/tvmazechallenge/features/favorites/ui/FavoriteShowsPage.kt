package com.matheus.tvmazechallenge.features.favorites.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.matheus.tvmazechallenge.features.favorites.viewmodel.FavoriteTVShowsViewModel
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.components.ErrorMessageWithRetry
import com.matheus.tvmazechallenge.shared.components.ImageDescriptionItem
import com.matheus.tvmazechallenge.shared.components.Loading

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteShowsPage(
    onTVShowClicked: (TVShowEntity) -> Unit
) {
    val viewModel: FavoriteTVShowsViewModel = hiltViewModel()
    val favoriteTVShowsState: StateData<List<TVShowEntity>> =
        viewModel.favoriteTVShowsResult.observeAsState(StateData.Loading()).value

    when (favoriteTVShowsState) {
        is StateData.Success -> {
            val listState = rememberLazyListState()
            LazyVerticalGrid(cells = GridCells.Fixed(2), state = listState) {
                items(favoriteTVShowsState.data) {
                    ImageDescriptionItem(
                        imageUrl = it.poster,
                        description = it.name
                    ) { onTVShowClicked(it) }
                }
            }
        }
        is StateData.Failure -> ErrorMessageWithRetry(errorMessage = favoriteTVShowsState.message) {
            viewModel.fetchFavoriteTVShows()
        }
        is StateData.Loading -> Loading()
    }
}