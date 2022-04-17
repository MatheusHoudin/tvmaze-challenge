package com.matheus.tvmazechallenge.features.tvshowdetails.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matheus.tvmazechallenge.shared.util.AppColors

private val episodeScheduleDays = listOf(
    EpisodeSchedule("Monday", false),
    EpisodeSchedule("Tuesday", false),
    EpisodeSchedule("Wednesday", false),
    EpisodeSchedule("Thursday", false),
    EpisodeSchedule("Friday", false),
    EpisodeSchedule("Saturday", false),
    EpisodeSchedule("Sunday", false)
)

private data class EpisodeSchedule(
    var day: String,
    var isEnabled: Boolean
)

@Composable
fun TVShowEpisodeScheduleList(episodeSchedule: List<String>) {
    LazyRow(
        modifier = Modifier.padding(top = 8.dp).height(36.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(getEpisodeScheduleItems(episodeSchedule)) {
            TVShowEpisodeSchedule(it)
        }
    }
}

private fun getEpisodeScheduleItems(episodeSchedule: List<String>): List<EpisodeSchedule> {
    episodeScheduleDays.forEachIndexed { index, item ->
        if (episodeSchedule.contains(item.day)) {
            episodeScheduleDays[index].isEnabled = true
        }
        episodeScheduleDays[index].day =
            episodeScheduleDays[index].day.substring(0, 3)
    }
    return episodeScheduleDays
}

@Composable
private fun TVShowEpisodeSchedule(episodeSchedule: EpisodeSchedule) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(getBackgroundColor(episodeSchedule.isEnabled)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = episodeSchedule.day,
            modifier = Modifier.width(50.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

private fun getBackgroundColor(isEpisodeDayEnabled: Boolean): Color =
    if (isEpisodeDayEnabled) AppColors.tvMazeSecondaryColor else AppColors.episodeDisabledColor

@Preview
@Composable
fun TVShowEpisodeSchedulePreview() {
    TVShowEpisodeScheduleList(
        episodeSchedule = listOf(
            "Monday",
            "Wednesday",
            "Saturday"
        )
    )
}