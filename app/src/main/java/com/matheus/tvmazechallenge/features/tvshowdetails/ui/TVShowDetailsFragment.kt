package com.matheus.tvmazechallenge.features.tvshowdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.TvShowDetailsFragmentBinding
import com.matheus.tvmazechallenge.features.favorites.viewmodel.FavoriteTVShowsViewModel
import com.matheus.tvmazechallenge.features.tvshowdetails.ui.components.TVShowEpisodeAdapter
import com.matheus.tvmazechallenge.features.tvshowdetails.ui.components.TVShowEpisodeScheduleAdapter
import com.matheus.tvmazechallenge.features.tvshowdetails.ui.components.TVShowGenreAdapter
import com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel.TVShowDetailsViewModel
import com.matheus.tvmazechallenge.shared.base.StateData
import com.matheus.tvmazechallenge.shared.extensions.toSeasonList
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowDetailsFragment : Fragment() {

    private val tvShowEpisodesAdapter = TVShowEpisodeAdapter()
    private val tvShowEpisodeScheduleAdapter = TVShowEpisodeScheduleAdapter()
    private val tvShowGenreAdapter = TVShowGenreAdapter()
    private val favoriteViewModel: FavoriteTVShowsViewModel by viewModel()
    private val viewModel: TVShowDetailsViewModel by viewModel()
    private val args: TVShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = TvShowDetailsFragmentBinding.inflate(inflater, container, false).also {
        configureBindings(it)
        configureTVShowEpisodesListener(it)
    }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTVShowEpisodes(args.tvShow.id)
    }

    private fun configureBindings(binding: TvShowDetailsFragmentBinding) = with(binding) {
        tvShowDetails = args.tvShow
        favoriteViewModel.getFavoriteTVShow(args.tvShow.id)
        tvShowDetailsRvGenres.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = tvShowGenreAdapter.apply {
                addItems(args.tvShow.genres)
            }
        }
        tvShowDetailsRvEpisodesSchedule.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = tvShowEpisodeScheduleAdapter.apply {
                setEpisodeScheduleItems(args.tvShow.schedule.days)
            }
        }
        tvShowDetailsSpSeasons.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setSelectedSeasonIndex(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
        tvShowsDetailsEmRetry.setOnRetryClickListener { viewModel.fetchTVShowEpisodes(args.tvShow.id) }
        tvShowDetailsIvBack.setOnClickListener {
            findNavController().popBackStack()
        }
        tvShowDetailsIvFavoriteTvShow.setOnClickListener {
            favoriteViewModel.updateFavoriteTVShow(args.tvShow)
        }
    }

    private fun configureTVShowEpisodesListener(binding: TvShowDetailsFragmentBinding) =
        with(binding) {
            viewModel.tvShowDetailsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is StateData.Success -> {
                        isLoadingEpisodes = false
                        thereIsError = false
                        fillSeasonsSpinner(binding, it.data.toSeasonList())
                    }
                    is StateData.Loading -> {
                        isLoadingEpisodes = true
                        thereIsError = false
                    }
                    is StateData.Failure -> {
                        isLoadingEpisodes = false
                        thereIsError = true
                        tvShowsDetailsEmRetry.setErrorMessage(it.message)
                    }
                }
            }
            viewModel.selectedEpisodes.observe(viewLifecycleOwner) { episodes ->
                tvShowDetailsRvEpisodes.apply {
                    layoutManager =
                        object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
                            override fun canScrollVertically() = false
                        }
                    adapter = tvShowEpisodesAdapter.apply {
                        addItems(episodes)
                    }
                }
            }
            favoriteViewModel.favoriteEnabled.observe(viewLifecycleOwner) { isFavoriteTVShow ->
                tvShowDetailsIvFavoriteTvShow.setImageResource(
                    if (isFavoriteTVShow)
                        R.drawable.ic_favorite_enabled
                    else
                        R.drawable.ic_favorite_disabled
                )
            }
            favoriteViewModel.isFavoriteAdded.observe(viewLifecycleOwner) { isAddedToFavorites ->
                val message =
                    if (isAddedToFavorites)
                        R.string.tv_show_details_tv_show_added_to_favorites
                    else
                        R.string.tv_show_details_tv_show_removed_to_favorites
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }

    private fun fillSeasonsSpinner(binding: TvShowDetailsFragmentBinding, seasons: List<String>) {
        binding.tvShowDetailsSpSeasons.adapter =
            ArrayAdapter(
                requireContext(),
                R.layout.tv_show_season_selected_layout,
                seasons
            ).apply {
                setDropDownViewResource(R.layout.tv_show_season_unselected_layout)
            }
    }
}