package com.matheus.tvmazechallenge.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matheus.tvmazechallenge.databinding.SearchShowsFragmentBinding
import com.matheus.tvmazechallenge.features.search.viewmodel.SearchTVShowsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchShowsFragment : Fragment() {

    private val viewModel: SearchTVShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SearchShowsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}