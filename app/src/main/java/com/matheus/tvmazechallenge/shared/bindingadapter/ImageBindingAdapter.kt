package com.matheus.tvmazechallenge.shared.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .transform(RoundedCorners(20))
        .into(view)
}