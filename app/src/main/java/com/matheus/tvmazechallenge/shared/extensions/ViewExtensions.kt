package com.matheus.tvmazechallenge.shared.extensions

import android.view.View

fun View.isVisible() {
    visibility = View.VISIBLE
}

fun View.isGone() {
    visibility = View.GONE
}