package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepository
import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepositoryImpl
import com.matheus.tvmazechallenge.features.tvshowdetails.repository.TVShowDetailsRepository
import com.matheus.tvmazechallenge.features.tvshowdetails.repository.TVShowDetailsRepositoryImpl
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowRepositoryImpl
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowsRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single<TVShowsRepository> { TVShowRepositoryImpl(get()) }
        single<SearchTVShowRepository> { SearchTVShowRepositoryImpl(get()) }
        single<TVShowDetailsRepository> { TVShowDetailsRepositoryImpl(get()) }
    }
}