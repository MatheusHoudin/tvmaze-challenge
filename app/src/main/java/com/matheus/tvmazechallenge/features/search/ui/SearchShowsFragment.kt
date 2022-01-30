package com.matheus.tvmazechallenge.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.SearchShowsFragmentBinding
import com.matheus.tvmazechallenge.features.search.viewmodel.SearchTVShowsViewModel
import com.matheus.tvmazechallenge.shared.adapter.TVShowAdapter
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchShowsFragment : Fragment() {

    private val tvMazeShowAdapter = TVShowAdapter()
    private val viewModel: SearchTVShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SearchShowsFragmentBinding.inflate(inflater, container, false)

        configureSearch(binding)
        configureBindings(binding)
        configureSearchShowsListener(binding)

        return binding.root
    }

    private fun configureSearch(binding: SearchShowsFragmentBinding) {
        binding.searchShowsEtSearch.doOnTextChanged { search, _, _, _ ->
            viewModel.searchShows(search.toString())
        }
    }

    private fun configureBindings(binding: SearchShowsFragmentBinding) = with(binding) {
        thereIsError = false
        shouldHideInstructions = false
        with(searchShowsFragmentEmRetry) {
            setOnRetryClickListener {
                viewModel.searchShows(searchShowsEtSearch.text.toString())
            }
        }
        searchShowsFragmentRvShows.apply {
            layoutManager = GridLayoutManager(context, TV_SHOWS_PER_ROW)
            adapter = tvMazeShowAdapter.apply {
                onClickListener = { tvShow ->
                    SearchShowsFragmentDirections.actionSearchShowsToTvShowDetails(tvShow).let {
                        findNavController().navigate(it)
                    }
                }
            }
        }
    }

    private fun configureSearchShowsListener(binding: SearchShowsFragmentBinding) = with(binding) {
        viewModel.showsResult.observe(viewLifecycleOwner) {
            shouldHideInstructions = true
            searchShowsFragmentEmRetry.hideRetryButton()
            searchShowsTvStartSearching.visibility = View.GONE
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
                    with(searchShowsFragmentEmRetry) {
                        Failure.notFoundShowsFailure
                        showRetryButton()
                        setErrorMessage(it.message)
                    }
                    thereIsError = true
                    isLoadingShows = false
                }
            }
        }
    }

    private companion object {
        const val TV_SHOWS_PER_ROW = 2
    }
}