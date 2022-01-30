package com.matheus.tvmazechallenge.features.tvshowdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheus.tvmazechallenge.databinding.TvShowDetailsFragmentBinding
import com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel.TVShowDetailsViewModel
import com.matheus.tvmazechallenge.shared.base.StateData
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowDetailsFragment : Fragment() {

    private val tvShowEpisodeScheduleAdapter = TVShowEpisodeScheduleAdapter()
    private val tvShowGenreAdapter = TVShowGenreAdapter()
    private val viewModel: TVShowDetailsViewModel by viewModel()
    private val args: TVShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = TvShowDetailsFragmentBinding.inflate(inflater, container, false)
        configureBindings(binding)
        configureTVShowEpisodesListener(binding)
        return binding.root
    }

    private fun configureBindings(binding: TvShowDetailsFragmentBinding) = with(binding) {
        tvShowDetails = args.tvShow
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
    }

    private fun configureTVShowEpisodesListener(binding: TvShowDetailsFragmentBinding) =
        with(binding) {
            viewModel.tvShowDetailsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is StateData.Success -> {
                        isLoadingEpisodes = false

                    }
                    is StateData.Loading -> {
                        isLoadingEpisodes = true
                    }
                    is StateData.Failure -> {
                        isLoadingEpisodes = false
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTVShowEpisodes(args.tvShow.id)
    }
}