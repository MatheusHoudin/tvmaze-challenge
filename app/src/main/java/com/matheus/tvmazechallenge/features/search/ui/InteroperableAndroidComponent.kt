package com.matheus.tvmazechallenge.features.search.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.matheus.tvmazechallenge.R
import kotlinx.android.synthetic.main.interoperable_android_component_layout.view.*

class InteroperableAndroidComponent(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {

    init {
       inflate(context, R.layout.interoperable_android_component_layout, this)

        interoperable_android_component_button.setOnClickListener {
            Toast.makeText(context, "Native toast", Toast.LENGTH_LONG).show()
        }
    }

    fun setMessage(message: String) {
        interoperable_android_component_text.text = message
    }
}