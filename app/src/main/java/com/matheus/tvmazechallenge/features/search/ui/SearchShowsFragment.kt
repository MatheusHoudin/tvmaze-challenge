package com.matheus.tvmazechallenge.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.SearchShowsFragmentBinding
import com.matheus.tvmazechallenge.features.search.viewmodel.SearchTVShowsViewModel
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.adapter.TVShowAdapter
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.components.ImageDescriptionItem
import com.matheus.tvmazechallenge.shared.error.Failure
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalFoundationApi::class)
class SearchShowsFragment : Fragment() {

    private val viewModel: SearchTVShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = SearchShowsFragmentBinding.inflate(inflater, container, false).also {
        configureSearch(it)
        configureBindings(it)
        configureSearchShowsListener(it)
    }.root

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
        searchShowsFragmentListShows.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        }
    }

    private fun configureSearchShowsListener(binding: SearchShowsFragmentBinding) = with(binding) {
        viewModel.showsResult.observe(viewLifecycleOwner) {
            shouldHideInstructions = true
            searchShowsTvStartSearching.visibility = View.GONE
            when (it) {
                is StateData.Success -> {
                    isLoadingShows = false
                    thereIsError = false
                    searchShowsFragmentListShows.setContent {
                        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                            items(it.data) { tvShowEntity ->
                                ImageDescriptionItem(tvShowEntity = tvShowEntity) {
                                    onTVShowClicked(
                                        tvShowEntity
                                    )
                                }
                            }
                        }
                    }
                }
                is StateData.Loading -> {
                    thereIsError = false
                    isLoadingShows = true
                }
                is StateData.Failure -> {
                    with(searchShowsFragmentEmRetry) {
                        if (it == Failure.notFoundShowsFailure) {
                            hideRetryButton()
                        }
                        setErrorMessage(it.message)
                    }
                    thereIsError = true
                    isLoadingShows = false
                }
            }
        }
    }

    private fun onTVShowClicked(tvShow: TVShowEntity) {
        SearchShowsFragmentDirections.actionSearchShowsToTvShowDetails(tvShow).let {
            findNavController().navigate(it)
        }
    }
}