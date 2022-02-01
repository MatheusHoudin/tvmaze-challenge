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
import org.koin.dsl.module

object DatasourceModule {
    val datasourceModule = module {
        single<TVShowsRemoteDatasource> { TVShowsRemoteDatasourceImpl(get()) }
        single<SearchTVShowRemoteDatasource> { SearchTVShowRemoteDatasourceImpl(get()) }
        single<TVShowDetailsRemoteDatasource> { TVShowDetailsRemoteDatasourceImpl(get()) }
        single<PeopleRemoteDatasource> { PeopleRemoteDatasourceImpl(get()) }
        single<PersonDetailsRemoteDatasource> { PersonDetailsRemoteDatasourceImpl(get()) }
        single<FavoriteTVShowsLocalDatasource> { FavoriteTVShowsLocalDatasourceImpl(get()) }
    }
}