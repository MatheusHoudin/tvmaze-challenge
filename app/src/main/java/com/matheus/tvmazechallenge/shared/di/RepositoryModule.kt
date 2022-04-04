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
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowsRepository
import com.matheus.tvmazechallenge.features.tvshows.repository.TVShowsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTVShowsRepository(impl: TVShowsRepositoryImpl): TVShowsRepository

    @Binds
    abstract fun bindSearchTVShowRepository(impl: SearchTVShowRepositoryImpl): SearchTVShowRepository

    @Binds
    abstract fun bindTVShowDetailsRepository(impl: TVShowDetailsRepositoryImpl): TVShowDetailsRepository

    @Binds
    abstract fun bindPeopleRepository(impl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    abstract fun bindPersonDetailsRepository(impl: PersonDetailsRepositoryImpl): PersonDetailsRepository

    @Binds
    abstract fun bindFavoriteTVShowsRepository(impl: FavoriteTVShowsRepositoryImpl): FavoriteTVShowsRepository
}