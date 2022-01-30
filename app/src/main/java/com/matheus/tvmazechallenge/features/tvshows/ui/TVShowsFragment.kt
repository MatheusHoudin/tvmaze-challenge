package com.matheus.tvmazechallenge.features.tvshows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.TvShowsFragmentBinding
import com.matheus.tvmazechallenge.features.tvshows.viewmodel.TVShowsViewModel
import com.matheus.tvmazechallenge.shared.adapter.TVShowAdapter
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.util.EndOfScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowsFragment : Fragment() {

    private val tvMazeShowAdapter = TVShowAdapter()
    private val viewModel: TVShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = TvShowsFragmentBinding.inflate(inflater, container, false)
        configureBindings(binding)
        configureTVMazeShowListener(binding)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTVShows()
    }

    private fun configureBindings(binding: TvShowsFragmentBinding) = with(binding) {
        thereIsError = false
        tvShowsFragmentEmRetry.setOnRetryClickListener { fetchTVShows() }
        tvShowsFragmentRvShows.apply {
            val tvShowsLayoutManager = GridLayoutManager(context, TV_SHOWS_PER_ROW)
            layoutManager = tvShowsLayoutManager
            adapter = tvMazeShowAdapter.apply {
                onClickListener = { tvShow ->
                    TVShowsFragmentDirections.actionTvShowToTvShowDetails(tvShow).let {
                        findNavController().navigate(it)
                    }
                }
            }
            addOnScrollListener(EndOfScrollListener(tvShowsLayoutManager) {
                fetchTVShows()
            })
        }
    }

    private fun configureTVMazeShowListener(binding: TvShowsFragmentBinding) = with(binding) {
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
                    tvShowsFragmentEmRetry.setErrorMessage(it.message)
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