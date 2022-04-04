package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.favorites.datasource.FavoriteTVShowsLocalDatasource
import com.matheus.tvmazechallenge.features.favorites.datasource.FavoriteTVShowsLocalDatasourceImpl
import com.matheus.tvmazechallenge.features.people.datasource.PeopleRemoteDatasource
import com.matheus.tvmazechallenge.features.people.datasource.PeopleRemoteDatasourceImpl
import com.matheus.tvmazechallenge.features.persondetails.datasource.PersonDetailsRemoteDatasource
import com.matheus.tvmazechallenge.features.persondetails.datasource.PersonDetailsRemoteDatasourceImpl
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowRemoteDatasource
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowRemoteDatasourceImpl
import com.matheus.tvmazechallenge.features.tvshowdetails.datasource.TVShowDetailsRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshowdetails.datasource.TVShowDetailsRemoteDatasourceImpl
import com.matheus.tvmazechallenge.features.tvshows.datasource.TVShowsRemoteDatasource
import com.matheus.tvmazechallenge.features.tvshows.datasource.TVShowsRemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun bindTVShowsRemoteDatasource(impl: TVShowsRemoteDatasourceImpl): TVShowsRemoteDatasource

    @Binds
    abstract fun bindSearchTVShowRemoteDatasource(impl: SearchTVShowRemoteDatasourceImpl): SearchTVShowRemoteDatasource

    @Binds
    abstract fun bindTVShowDetailsRemoteDatasource(impl: TVShowDetailsRemoteDatasourceImpl): TVShowDetailsRemoteDatasource

    @Binds
    abstract fun bindPeopleRemoteDatasource(impl: PeopleRemoteDatasourceImpl): PeopleRemoteDatasource

    @Binds
    abstract fun bindPersonDetailsRemoteDatasource(impl: PersonDetailsRemoteDatasourceImpl): PersonDetailsRemoteDatasource

    @Binds
    abstract fun bindFavoriteTVShowsLocalDatasource(impl: FavoriteTVShowsLocalDatasourceImpl): FavoriteTVShowsLocalDatasource
}