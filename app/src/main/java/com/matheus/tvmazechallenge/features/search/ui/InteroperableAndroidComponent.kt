package com.matheus.tvmazechallenge.features.search.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.matheus.tvmazechallenge.R

class InteroperableAndroidComponent(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    init {
       inflate(context, R.layout.interoperable_android_component_layout, this)
    }

    fun setMessage(message: String) {

    }
}