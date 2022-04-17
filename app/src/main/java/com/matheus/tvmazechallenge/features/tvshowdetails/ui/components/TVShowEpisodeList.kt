package com.matheus.tvmazechallenge.features.tvshowdetails.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.rememberGlidePainter
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity

@Composable
fun TVShowEpisodeList(episodes: List<TVShowEpisodeEntity>) {
    LazyColumn() {
        items(episodes) { episode ->
            TVShowEpisodeComposable(tvShowEpisodeEntity = episode)
        }
    }
}

@Composable
private fun TVShowEpisodeComposable(tvShowEpisodeEntity: TVShowEpisodeEntity) {
    Column(
        modifier = Modifier
            .padding(bottom = 18.dp)
            .fillMaxWidth()
    ) {
        TVShowEpisodeHeader(
            imageUrl = tvShowEpisodeEntity.image,
            episodeName = tvShowEpisodeEntity.name,
            episodeNumber = tvShowEpisodeEntity.number
        )
        TVShowEpisodeDescription(tvShowEpisodeEntity.summary)
    }
}

@Composable
private fun TVShowEpisodeHeader(imageUrl: String, episodeName: String, episodeNumber: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberGlidePainter(imageUrl),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(180.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = stringResource(
                id = R.string.tv_show_details_episode_tv_episode_name,
                episodeNumber,
                episodeName
            ),
            modifier = Modifier.padding(start = 12.dp),
            style = MaterialTheme.typography.subtitle1,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Composable
private fun TVShowEpisodeDescription(episodeDescription: String) {
    Text(
        text = episodeDescription,
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.subtitle2,
        fontSize = 14.sp,
        color = Color.White
    )
}