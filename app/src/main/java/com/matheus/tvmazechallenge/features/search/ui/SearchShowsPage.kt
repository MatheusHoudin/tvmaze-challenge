package com.matheus.tvmazechallenge.features.search.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun SearchShowsPage() {
    var textVisibility: Boolean by remember { mutableStateOf(false) }
    var notAffectedText: String by remember { mutableStateOf("Not affected")}
    Column {
        MyText(notAffectedText)
        InvisibleText(isVisible = textVisibility)
        ChangeVisibilityButton {
            textVisibility = !textVisibility
            //notAffectedText = "$notAffectedText - new"
        }
    }
}

@Composable
private fun MyText(text: String) {
    Log.e("MyText", "MyTextCalled")
    Text(
        text = text,
        color = Color.White
    )
}

@Composable
private fun InvisibleText(isVisible: Boolean) {
    Log.e("InvisibleText", "InvisibleTextCalled")
    AnimatedVisibility(visible = isVisible) {
        Text(
            text = "Hey, I'm an invisible text",
            color = Color.White
        )
    }
}

@Composable
private fun ChangeVisibilityButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() }
    ) {
        Text(text = "Change visibility")
    }
}