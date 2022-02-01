package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.favorites.viewmodel.FavoriteTVShowsViewModel
import com.matheus.tvmazechallenge.features.people.viewmodel.PeopleViewModel
import com.matheus.tvmazechallenge.features.persondetails.viewmodel.PersonDetailsViewModel
import com.matheus.tvmazechallenge.features.search.viewmodel.SearchTVShowsViewModel
import com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel.TVShowDetailsViewModel
import com.matheus.tvmazechallenge.features.tvshows.viewmodel.TVShowsViewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        factory { TVShowsViewModel(get()) }
        factory { SearchTVShowsViewModel(get()) }
        factory { TVShowDetailsViewModel(get()) }
        factory { PeopleViewModel(get()) }
        factory { PersonDetailsViewModel(get()) }
        factory { FavoriteTVShowsViewModel(get()) }
    }
}