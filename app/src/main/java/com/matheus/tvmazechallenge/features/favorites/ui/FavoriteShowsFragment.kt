package com.matheus.tvmazechallenge.features.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matheus.tvmazechallenge.databinding.FavoriteShowsFragmentBinding

class FavoriteShowsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FavoriteShowsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}