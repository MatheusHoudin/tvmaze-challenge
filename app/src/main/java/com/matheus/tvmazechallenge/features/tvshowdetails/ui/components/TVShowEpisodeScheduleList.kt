package com.matheus.tvmazechallenge.features.tvshowdetails.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TVShowEpisodeScheduleList(episodeSchedule: List<String>) {
    Row() {

    }
}

@Composable
private fun TVShowEpisodeSchedule(day: String) {
    Text(
        text = day,
        modifier = Modifier.size(
            width = 50.dp,
            height = 36.dp
        ),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1,
        fontSize = 12.sp,
        color = Color.White
    )
}

@Preview
@Composable
fun TVShowEpisodeSchedulePreview() {
    TVShowEpisodeScheduleList(episodeSchedule = listOf(
        "Monday",
        "Wednesday",
        "Saturday"
    ))
}