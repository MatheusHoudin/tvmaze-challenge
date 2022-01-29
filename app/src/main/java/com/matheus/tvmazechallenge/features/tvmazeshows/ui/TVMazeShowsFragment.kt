package com.matheus.tvmazechallenge.features.tvmazeshows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.TvmazeShowsFragmentBinding
import com.matheus.tvmazechallenge.features.tvmazeshows.viewmodel.TVMazeShowsViewModel
import com.matheus.tvmazechallenge.shared.adapter.TVMazeShowAdapter
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.util.EndOfScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVMazeShowsFragment : Fragment() {

    private val tvMazeShowAdapter = TVMazeShowAdapter()
    private val viewModel: TVMazeShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = TvmazeShowsFragmentBinding.inflate(inflater, container, false)
        configureBinding(binding)
        configureTVMazeShowListener(binding)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTVShows()
    }

    private fun configureBinding(binding: TvmazeShowsFragmentBinding) = with(binding) {
        thereIsError = false
        tvmazeShowsFragmentEmRetry.setOnRetryClickListener { fetchTVShows() }
        tvmazeShowsFragmentRvShows.apply {
            val tvShowsLayoutManager = GridLayoutManager(context, TV_SHOWS_PER_ROW)
            layoutManager = tvShowsLayoutManager
            adapter = tvMazeShowAdapter
            addOnScrollListener(EndOfScrollListener(tvShowsLayoutManager) {
                fetchTVShows()
            })
        }
    }

    private fun configureTVMazeShowListener(binding: TvmazeShowsFragmentBinding) = with(binding) {
        viewModel.showsResult.observe(viewLifecycleOwner) {
            when (it) {
                is StateData.Success -> {
                    isLoadingShows = false
                    thereIsError = false
                    tvMazeShowAdapter.addItems(it.data)
                }
                is StateData.Loading -> {
                    thereIsError = false
                    isLoadingShows = true
                }
                is StateData.Failure -> {
                    tvmazeShowsFragmentEmRetry.setErrorMessage(it.message)
                    thereIsError = true
                    isLoadingShows = false
                }
            }
        }
    }

    private fun fetchTVShows() = viewModel.fetchShows()

    private companion object {
        const val TV_SHOWS_PER_ROW = 2
    }
}