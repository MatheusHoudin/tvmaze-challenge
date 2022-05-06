package com.matheus.tvmazechallenge.features.persondetails.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.rememberGlidePainter
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.features.persondetails.entity.CastCreditsEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.util.AppColors

@Composable
fun CastCreditsShow(
    castCredits: List<CastCreditsEntity>,
    onSeeMoreShowDetailsClicked: (TVShowEntity) -> Unit,
    onOpenOnWebClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.height(400.dp)
    ) {
        items(castCredits) {
            CastCreditsShowItem(
                castCredit = it,
                onSeeMoreShowDetailsClicked = onSeeMoreShowDetailsClicked,
                onOpenOnWebClicked = onOpenOnWebClicked
            )
        }
    }
}

@Composable
private fun CastCreditsShowItem(
    castCredit: CastCreditsEntity,
    onSeeMoreShowDetailsClicked: (TVShowEntity) -> Unit,
    onOpenOnWebClicked: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .background(AppColors.tvMazeLightMainColor)
            .fillMaxWidth()
    ) {
        CastCreditImage(imageUrl = castCredit.tvShow.poster)
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CastCreditTitle(castCreditTitle = castCredit.tvShow.name)
            CastCreditRole(castCreditRole = castCredit.creditType)
            CastCreditsButton(
                text = stringResource(id = R.string.cast_credits_show_see_more_details),
                textColor = Color.White,
                backgroundColor = AppColors.tvMazeSecondaryColor,
                paddingTop = 20.dp,
                onClicked = {
                    onSeeMoreShowDetailsClicked(castCredit.tvShow)
                }
            )
            CastCreditsButton(
                text = stringResource(id = R.string.cast_credits_show_open_on_web),
                textColor = AppColors.tvMazeSecondaryColor,
                backgroundColor = Color.White,
                paddingTop = 0.dp,
                onClicked = {
                    onOpenOnWebClicked(castCredit.tvShow.url)
                }
            )
        }
    }
}

@Composable
private fun CastCreditImage(imageUrl: String) {
    Image(
        painter = rememberGlidePainter(imageUrl),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(150.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
private fun CastCreditTitle(castCreditTitle: String) {
    Text(
        text = castCreditTitle,
        style = MaterialTheme.typography.body1,
        color = Color.White,
        fontSize = 16.sp
    )
}

@Composable
private fun CastCreditRole(castCreditRole: String) {
    Text(
        text = castCreditRole,
        style = MaterialTheme.typography.subtitle2,
        color = Color.White,
        fontSize = 12.sp,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Composable
private fun CastCreditsButton(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    paddingTop: Dp,
    onClicked: () -> Unit
) {
    Button(
        onClick = { onClicked() },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = backgroundColor
        ),
        modifier = Modifier
            .padding(top = paddingTop)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}