package com.matheus.tvmazechallenge.features.tvshowdetails.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity

@Composable
fun TVShowDetailsHeader(
    tvShowEntity: TVShowEntity,
    isFavorite: Boolean?,
    onBackPressed: () -> Unit,
    onFavoriteClicked: () -> Unit
) {
    val favoriteIcon: Int =
        isFavorite?.let { if (it) R.drawable.ic_favorite_enabled else R.drawable.ic_favorite_disabled }
            ?: R.drawable.ic_favorite_disabled
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back button",
            modifier = Modifier
                .size(56.dp)
                .padding(top = 20.dp)
                .clickable {
                    onBackPressed()
                }
        )
        Image(
            rememberGlidePainter(tvShowEntity.poster),
            contentDescription = "TV Show poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .height(300.dp)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Image(
            painter = painterResource(id = favoriteIcon),
            contentDescription = "Favorite button",
            modifier = Modifier
                .size(56.dp)
                .padding(top = 20.dp)
                .clickable {
                    onFavoriteClicked()
                }
        )
    }
}