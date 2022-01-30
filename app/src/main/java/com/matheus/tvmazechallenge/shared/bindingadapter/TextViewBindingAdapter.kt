package com.matheus.tvmazechallenge.shared.bindingadapter

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun TextView.setHtmlText(htmlText: String) {
    text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
}