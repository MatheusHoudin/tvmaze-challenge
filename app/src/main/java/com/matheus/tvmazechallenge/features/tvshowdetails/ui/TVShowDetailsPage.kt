package com.matheus.tvmazechallenge.features.tvshowdetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.compose.rememberNavController
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.features.favorites.viewmodel.FavoriteTVShowsViewModel
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.ui.components.*
import com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel.TVShowDetailsViewModel
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.components.Loading
import com.matheus.tvmazechallenge.shared.extensions.toSeasonList
import com.matheus.tvmazechallenge.shared.util.AppColors

@Composable
fun TVShowDetailsPage(
    tvShowEntity: TVShowEntity,
    viewModel: TVShowDetailsViewModel,
    favoriteTVShowViewModel: FavoriteTVShowsViewModel
) {
    val tvShowDetailsState: StateData<List<TVShowSeasonEpisodesEntity>> =
        viewModel.tvShowDetailsResult.observeAsState(StateData.Loading()).value
    val tvShowSelectedEpisodes: List<TVShowEpisodeEntity> =
        viewModel.selectedEpisodes.observeAsState(emptyList()).value

    val isFavorite: Boolean? = favoriteTVShowViewModel.favoriteEnabled.observeAsState().value

    ShowTVShowDetailsContent(
        tvShowEntity = tvShowEntity,
        isFavorite = isFavorite,
        state = tvShowDetailsState,
        selectedEpisodes = tvShowSelectedEpisodes,
        onSelectSeason = { viewModel.setSelectedSeasonIndex(it) },
        onFavoriteCLicked = { favoriteTVShowViewModel.updateFavoriteTVShow(tvShowEntity) }
    )
}

@Composable
private fun ShowTVShowDetailsContent(
    tvShowEntity: TVShowEntity,
    isFavorite: Boolean?,
    state: StateData<List<TVShowSeasonEpisodesEntity>>,
    selectedEpisodes: List<TVShowEpisodeEntity>,
    onSelectSeason: (Int) -> Unit,
    onFavoriteCLicked: () -> Unit
) {
    when (state) {
        is StateData.Success -> {
            PageContent(
                tvShowEntity = tvShowEntity,
                isFavorite = isFavorite,
                seasonList = state.data.toSeasonList(),
                selectedEpisodes = selectedEpisodes,
                onSelectSeason = onSelectSeason,
                onFavoriteCLicked = onFavoriteCLicked
            )
        }
        is StateData.Failure -> {}
        is StateData.Loading -> Loading()
    }
}

@Composable
private fun PageContent(
    tvShowEntity: TVShowEntity,
    isFavorite: Boolean?,
    seasonList: List<String>,
    selectedEpisodes: List<TVShowEpisodeEntity>,
    onSelectSeason: (Int) -> Unit,
    onFavoriteCLicked: () -> Unit
) {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .background(AppColors.tvMazeMainColor)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {
        TVShowDetailsHeader(
            tvShowEntity = tvShowEntity,
            isFavorite = isFavorite,
            onBackPressed = { navController.popBackStack() },
            onFavoriteClicked = onFavoriteCLicked
        )
        MovieName(movieName = tvShowEntity.name)
        TVShowGenreList(genres = tvShowEntity.genres)
        TVShowScheduleHeader(episodeScheduleTime = tvShowEntity.schedule.time)
        TVShowEpisodeScheduleList(episodeSchedule = tvShowEntity.schedule.days)
        SynopsisSection(summary = tvShowEntity.summary)
        CustomDivider()
        EpisodesTitle()
        EpisodesDropdown(
            seasonList = seasonList,
            onSelectItem = { onSelectSeason(it) }
        )
        EpisodesSection(episodes = selectedEpisodes)
    }
}

@Composable
private fun MovieName(movieName: String) {
    Text(
        text = movieName,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(top = 12.dp),
        color = Color.White,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun TVShowScheduleHeader(episodeScheduleTime: String) {
    Row(
        modifier = Modifier
            .padding(top = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(id = R.string.tv_show_details_episodes_schedule_title),
            color = Color.White,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(
                id = R.string.tv_show_details_episodes_schedule_time,
                episodeScheduleTime
            ),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            color = AppColors.tvMazeSecondaryColor,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun SynopsisSection(summary: String) {
    if (summary.isNotEmpty())
        Column {
            SynopsisTitle()
            SynopsisContent(summary = summary)
        }
}

@Composable
private fun SynopsisTitle() {
    Text(
        text = stringResource(R.string.tv_show_details_synopsis),
        fontSize = 16.sp,
        color = Color.White,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(top = 6.dp)
    )
}

@Composable
private fun SynopsisContent(summary: String) {
    Text(
        text = HtmlCompat.fromHtml(summary, 0).toString(),
        fontSize = 14.sp,
        textAlign = TextAlign.Justify,
        color = Color.White,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(top = 6.dp)
    )
}

@Composable
private fun CustomDivider() {
    Divider(
        modifier = Modifier
            .padding(top = 16.dp, start = 40.dp, end = 40.dp)
            .fillMaxWidth(),
        thickness = 2.dp,
        color = AppColors.tvMazeSecondaryColor
    )
}

@Composable
private fun EpisodesTitle() {
    Text(
        text = stringResource(R.string.tv_show_details_episodes),
        fontSize = 16.sp,
        color = Color.White,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
@Preview
fun MovieNamePreview() {
    MovieName("Movie Name")
}