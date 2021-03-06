package com.matheus.tvmazechallenge.features.tvshows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.GenericImageDescriptionListFragmentBinding
import com.matheus.tvmazechallenge.features.tvshows.viewmodel.TVShowsViewModel
import com.matheus.tvmazechallenge.shared.adapter.TVShowAdapter
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.util.EndOfScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowsFragment : Fragment() {

    private val tvShowAdapter = TVShowAdapter()
    private val viewModel: TVShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = GenericImageDescriptionListFragmentBinding.inflate(inflater, container, false).also {
        configureBindings(it)
        configureTVMazeShowListener(it)
    }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTVShows()
    }

    private fun configureBindings(binding: GenericImageDescriptionListFragmentBinding) =
        with(binding) {
            thereIsError = false
            genericImageDescriptionListFragmentEmRetry.setOnRetryClickListener { fetchTVShows() }
            genericImageDescriptionListFragmentRvShows.apply {
                val tvShowsLayoutManager = GridLayoutManager(context, TV_SHOWS_PER_ROW)
                layoutManager = tvShowsLayoutManager
                adapter = tvShowAdapter.apply {
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

    private fun configureTVMazeShowListener(binding: GenericImageDescriptionListFragmentBinding) =
        with(binding) {
            viewModel.showsResult.observe(viewLifecycleOwner) {
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
                        genericImageDescriptionListFragmentEmRetry.setErrorMessage(it.message)
                        thereIsError = true
                        isLoadingItems = false
                    }
                }
            }
        }

    private fun fetchTVShows() = viewModel.fetchShows()

    private companion object {
        const val TV_SHOWS_PER_ROW = 2
    }
}