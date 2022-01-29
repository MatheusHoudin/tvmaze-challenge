package com.matheus.tvmazechallenge.features.tvmazeshows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.matheus.tvmazechallenge.databinding.TvmazeShowsFragmentBinding
import com.matheus.tvmazechallenge.features.tvmazeshows.viewmodel.TVMazeShowsViewModel
import com.matheus.tvmazechallenge.shared.base.StateData
import org.koin.android.ext.android.bind
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
        fetchTVShows()
        configureTVShowsList(binding)
        configureTVMazeShowListener(binding)
        return binding.root
    }

    private fun fetchTVShows() = viewModel.fetchShows()

    private fun configureTVShowsList(binding: TvmazeShowsFragmentBinding) {
        binding.tvmazeShowsFragmentRvShows.apply {
            val tvShowsLayoutManager = GridLayoutManager(context, TV_SHOWS_PER_ROW)
            layoutManager = tvShowsLayoutManager
            adapter = tvMazeShowAdapter
        }
    }

    private fun configureTVMazeShowListener(binding: TvmazeShowsFragmentBinding) {
        viewModel.showsResult.observe(viewLifecycleOwner) {
            when (it) {
                is StateData.Success -> {
                    binding.isLoadingShows = false
                    tvMazeShowAdapter.addItems(it.data)
                }
                is StateData.Loading -> {
                    binding.isLoadingShows = true
                }
                is StateData.Failure -> {
                    binding.isLoadingShows = false
                }
            }
        }
    }

    private companion object {
        const val TV_SHOWS_PER_ROW = 2
    }
}