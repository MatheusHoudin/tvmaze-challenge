package com.matheus.tvmazechallenge.features.tvshowdetails.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matheus.tvmazechallenge.shared.util.AppColors

@Composable
fun TVShowGenreList(genres: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres) {
            TVShowGenre(genre = it)
        }
    }
}

@Composable
private fun TVShowGenre(genre: String) {
    Box(
        modifier = Modifier
            .background(AppColors.tvMazeLightMainColor, shape = RoundedCornerShape(20.dp))
            .height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = genre,
            color = Color.White,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 12.dp)
        )
    }
}

@Preview
@Composable
fun TVShowGenreListPreview() {
    TVShowGenreList(
        genres = listOf(
            "Horror",
            "Comedy",
            "Fantasy"
        )
    )
}

@Preview
@Composable
fun TVShowGenrePreview() {
    TVShowGenre(genre = "Fiction")
}