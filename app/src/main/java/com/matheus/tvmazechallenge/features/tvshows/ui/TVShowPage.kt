package com.matheus.tvmazechallenge.features.tvshows.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.viewmodel.TVShowsViewModel
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.components.ErrorMessageWithRetry
import com.matheus.tvmazechallenge.shared.components.ImageDescriptionItem
import com.matheus.tvmazechallenge.shared.components.Loading
import com.matheus.tvmazechallenge.shared.extensions.OnBottomReached

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TVShowPage(
    onTVShowClicked: (TVShowEntity) -> Unit
) {
    val viewModel: TVShowsViewModel = hiltViewModel()
    val showsState: StateData<List<TVShowEntity>> =
        viewModel.showsResult.observeAsState(StateData.Loading()).value

    when (showsState) {
        is StateData.Success -> {
            val listState = rememberLazyListState()
            LazyVerticalGrid(cells = GridCells.Fixed(2), state = listState) {
                items(showsState.data) {
                    ImageDescriptionItem(tvShowEntity = it) { onTVShowClicked(it) }
                }
            }
            listState.OnBottomReached {
                viewModel.fetchShows()
            }
        }
        is StateData.Failure -> ErrorMessageWithRetry(errorMessage = showsState.message) {
                viewModel.fetchShows()
            }
        is StateData.Loading -> Loading()
    }
}