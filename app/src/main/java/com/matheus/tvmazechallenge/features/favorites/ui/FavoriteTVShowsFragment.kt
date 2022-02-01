package com.matheus.tvmazechallenge.features.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.GenericImageDescriptionListFragmentBinding
import com.matheus.tvmazechallenge.features.favorites.viewmodel.FavoriteTVShowsViewModel
import com.matheus.tvmazechallenge.shared.adapter.TVShowAdapter
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.error.Failure
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTVShowsFragment : Fragment() {

    private val tvShowAdapter = TVShowAdapter()
    private val viewModel: FavoriteTVShowsViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        fetchFavoriteTVShows()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = GenericImageDescriptionListFragmentBinding.inflate(inflater, container, false).also {
        configureBindings(it)
        configureTVMazeShowListener(it)
    }.root

    private fun configureBindings(binding: GenericImageDescriptionListFragmentBinding) =
        with(binding) {
            thereIsError = false
            genericImageDescriptionListFragmentEmRetry.setOnRetryClickListener { fetchFavoriteTVShows() }
            genericImageDescriptionListFragmentRvShows.apply {
                layoutManager = GridLayoutManager(context, TV_SHOWS_PER_ROW)
                adapter = tvShowAdapter.apply {
                    onClickListener = { tvShow ->
                        FavoriteTVShowsFragmentDirections.actionFavoriteShowsToTvShowDetails(tvShow)
                            .let {
                                findNavController().navigate(it)
                            }
                    }
                }
            }
        }

    private fun configureTVMazeShowListener(binding: GenericImageDescriptionListFragmentBinding) =
        with(binding) {
            viewModel.favoriteTVShowsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is StateData.Success -> {
                        isLoadingItems = false
                        thereIsError = false
                        tvShowAdapter.addItems(it.data)
                    }
                    is StateData.Loading -> {
                        thereIsError = false
                        isLoadingItems = true
                    }
                    is StateData.Failure -> {
                        if (it == Failure.thereAreNoFavoriteTVShowsFailure) {
                            genericImageDescriptionListFragmentEmRetry.hideRetryButton()
                        }
                        genericImageDescriptionListFragmentEmRetry.setErrorMessage(it.message)
                        thereIsError = true
                        isLoadingItems = false
                    }
                }
            }
        }

    private fun fetchFavoriteTVShows() = viewModel.fetchFavoriteTVShows()

    private companion object {
        const val TV_SHOWS_PER_ROW = 2
    }
}