package com.matheus.tvmazechallenge.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.shared.util.AppColors

@Composable
fun ErrorMessageWithRetry(
    errorMessage: String,
    retryAction: () -> Unit
) {
    Column(
        modifier = Modifier.padding(12.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1
        )
        Button(
            onClick = { retryAction() },
            modifier = Modifier.padding(top = 12.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = AppColors.tvMazeSecondaryColor
            )
        ) {
            Text(
                text = stringResource(id = R.string.error_message_with_retry_button_name),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun ErrorMessageWithRetryPreview() {
    MaterialTheme {
        ErrorMessageWithRetry(errorMessage = "A descriptive error message about what happened") {

        }
    }
}
