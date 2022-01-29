package com.matheus.tvmazechallenge.shared.components

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.matheus.tvmazechallenge.R
import kotlinx.android.synthetic.main.error_message_with_retry_layout.view.*

class ErrorMessageWithRetry(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.error_message_with_retry_layout, this)
    }

    fun setErrorMessage(errorMessage: String) {
        error_message_with_retry_tv_message.text = errorMessage
    }

    fun setOnRetryClickListener(retryClickAction: () -> Unit) {
        error_message_with_retry_bt_retry.setOnClickListener {
            retryClickAction()
        }
    }
}