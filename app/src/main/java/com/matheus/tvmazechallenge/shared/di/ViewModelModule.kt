package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.search.viewmodel.SearchTVShowsViewModel
import com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel.TVShowDetailsViewModel
import com.matheus.tvmazechallenge.features.tvshows.viewmodel.TVShowsViewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        single { TVShowsViewModel(get()) }
        single { SearchTVShowsViewModel(get()) }
        single { TVShowDetailsViewModel(get()) }
    }
}