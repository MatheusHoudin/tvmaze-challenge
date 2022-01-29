package com.matheus.tvmazechallenge.shared.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EndOfScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val callback: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var pastVisibleItems = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var job: Job? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy > SCROLLING_DOWN_MINIMUM_DY) {
            visibleItemCount = layoutManager.childCount
            totalItemCount = layoutManager.itemCount
            pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                job?.cancel()
                job = MainScope().launch {
                    delay(CALLBACK_TRIGGER_DELAY)
                    callback()
                }
            }
        }
    }

    private companion object {
        private const val CALLBACK_TRIGGER_DELAY = 500L
        private const val SCROLLING_DOWN_MINIMUM_DY = 0
    }
}