package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.favorites.repository.FavoriteTVShowsRepository
import com.matheus.tvmazechallenge.features.favorites.repository.FavoriteTVShowsRepositoryImpl
import com.matheus.tvmazechallenge.features.people.repository.PeopleRepository
import com.matheus.tvmazechallenge.features.people.repository.PeopleRepositoryImpl
import com.matheus.tvmazechallenge.features.persondetails.repository.PersonDetailsRepository
import com.matheus.tvmazechallenge.features.persondetails.repository.PersonDetailsRepositoryImpl
import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepository
import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepositoryImpl
import com.matheus.tvmazechallenge.features.tvshowdetails.repository.TVShowDetailsRepository
import com.matheus.tvmazechallenge.features.tvshowdetails.repository.TVShowDetailsRepositoryImpl
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowsRepositoryImpl
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowsRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single<TVShowsRepository> { TVShowsRepositoryImpl(get()) }
        single<SearchTVShowRepository> { SearchTVShowRepositoryImpl(get()) }
        single<TVShowDetailsRepository> { TVShowDetailsRepositoryImpl(get()) }
        single<PeopleRepository> { PeopleRepositoryImpl(get()) }
        single<PersonDetailsRepository> { PersonDetailsRepositoryImpl(get()) }
        single<FavoriteTVShowsRepository> { FavoriteTVShowsRepositoryImpl(get()) }
    }
}