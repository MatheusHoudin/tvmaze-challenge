package com.matheus.tvmazechallenge.features.tvshowdetails.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.google.accompanist.glide.rememberGlidePainter
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.shared.util.AppColors

@Composable
fun EpisodesDropdown(
    seasonList: List<String>,
    onSelectItem: (Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedSeason by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .clickable { isExpanded = true }
        ) {
            Text(text = seasonList[selectedSeason])
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
        }
        EpisodesDropdownMenu(
            isExpanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            onSelectItem = {
                selectedSeason = it
                onSelectItem(it)
            },
            seasonList = seasonList
        )
    }
}

@Composable
fun EpisodesDropdownMenu(
    isExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onSelectItem: (Int) -> Unit,
    seasonList: List<String>,
) {
    DropdownMenu(expanded = isExpanded, onDismissRequest = { onDismissRequest() }) {
        seasonList.forEachIndexed { index, season ->
            Text(
                text = season,
                style = MaterialTheme.typography.subtitle1,
                color = AppColors.tvMazeMainColor,
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.White)
                    .clickable {
                        onSelectItem(index)
                        onDismissRequest()
                    }
            )
        }
    }
}

@Composable
fun EpisodesSection(episodes: List<TVShowEpisodeEntity>) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .height(400.dp)
    ) {
        items(episodes) {
            EpisodeItem(episode = it)
        }
    }
}

@Composable
fun EpisodeItem(episode: TVShowEpisodeEntity) {
    Column(
        modifier = Modifier
            .padding(bottom = 18.dp)
            .background(AppColors.tvMazeMainColor)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberGlidePainter(episode.image),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(180.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = stringResource(
                    R.string.tv_show_details_episode_tv_episode_name,
                    episode.number,
                    episode.name
                ),
                color = Color.White,
                maxLines = 4,
                fontSize = 14.sp,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            )
        }
        Text(
            text = HtmlCompat.fromHtml(episode.summary, 0).toString(),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun EpisodesDropdownPreview() {
    EpisodesDropdown(
        seasonList = listOf("Season 1", "Season 2", "Season 3"),
        onSelectItem = {}
    )
}

@Preview
@Composable
fun EpisodesDropdownMenuPreview() {
    EpisodesDropdownMenu(
        isExpanded = true,
        onDismissRequest = {},
        onSelectItem = {},
        seasonList = listOf("Season 1", "Season 2", "Season 3")
    )
}