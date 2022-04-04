package com.matheus.tvmazechallenge.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.util.AppColors

@Composable
fun ImageDescriptionItem(
    tvShowEntity: TVShowEntity,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 4.dp,
                vertical = 12.dp
            )
            .background(AppColors.tvMazeMainColor)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberGlidePainter(tvShowEntity.poster),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.width(150.dp).height(200.dp).clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = tvShowEntity.name,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    MaterialTheme {
        ImageDescriptionItem(
            TVShowEntity(
                id = 1,
                name = "A cool item",
                poster = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fandroid-developers.googleblog.com%2F2020%2F08%2Fannouncing-jetpack-compose-alpha.html&psig=AOvVaw2d7soXvIO_7z79WVfVfZS1&ust=1648588958608000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCKimkMze6fYCFQAAAAAdAAAAABAD",
                url = "",
                schedule = TVShowScheduleEntity("", emptyList()),
                genres = emptyList(),
                summary = ""
            )
        ) {

        }
    }
}