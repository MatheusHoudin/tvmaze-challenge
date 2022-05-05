package com.matheus.tvmazechallenge.features.people.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.features.people.viewmodel.PeopleViewModel
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.components.ErrorMessageWithRetry
import com.matheus.tvmazechallenge.shared.components.ImageDescriptionItem
import com.matheus.tvmazechallenge.shared.components.Loading
import com.matheus.tvmazechallenge.shared.extensions.OnBottomReached

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PeoplePage(
    onPersonClick: (PersonEntity) -> Unit
) {
    val viewModel: PeopleViewModel = hiltViewModel()
    val peopleState: StateData<List<PersonEntity>> =
        viewModel.peopleResult.observeAsState(StateData.Loading()).value

    when (peopleState) {
        is StateData.Success -> {
            val listState = rememberLazyListState()
            LazyVerticalGrid(cells = GridCells.Fixed(2), state = listState) {
                items(peopleState.data) {
                    ImageDescriptionItem(
                        imageUrl = it.image,
                        description = it.name
                    ) { onPersonClick(it) }
                }
            }
        }
        is StateData.Failure -> ErrorMessageWithRetry(errorMessage = peopleState.message) {
            viewModel.fetchPeople()
        }
        is StateData.Loading -> Loading()
    }
}