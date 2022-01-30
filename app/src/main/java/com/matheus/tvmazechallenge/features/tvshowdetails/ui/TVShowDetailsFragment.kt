package com.matheus.tvmazechallenge.features.tvshowdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.matheus.tvmazechallenge.databinding.TvShowDetailsFragmentBinding
import com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel.TVShowDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowDetailsFragment : Fragment() {

    private val viewModel: TVShowDetailsViewModel by viewModel()
    private val args: TVShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = TvShowDetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun configureTVShowDetailsListener(binding: TvShowDetailsFragmentBinding) =
        with(binding) {
            viewModel.tvShowDetailsResult.observe(viewLifecycleOwner) {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTVShowEpisodes(args.tvShow.id)
    }
}