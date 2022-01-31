package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.people.datasource.PeopleDatasource
import com.matheus.tvmazechallenge.features.people.datasource.PeopleDatasourceImpl
import com.matheus.tvmazechallenge.features.persondetails.datasource.PersonDetailsDatasource
import com.matheus.tvmazechallenge.features.persondetails.datasource.PersonDetailsDatasourceImpl
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowDatasource
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowDatasourceImpl
import com.matheus.tvmazechallenge.features.tvshowdetails.datasource.TVShowDetailsDatasource
import com.matheus.tvmazechallenge.features.tvshowdetails.datasource.TVShowDetailsDatasourceImpl
import com.matheus.tvmazechallenge.features.tvshows.datasource.TVShowsDatasource
import com.matheus.tvmazechallenge.features.tvshows.datasource.TVShowsDatasourceImpl
import org.koin.dsl.module

object DatasourceModule {
    val datasourceModule = module {
        single<TVShowsDatasource> { TVShowsDatasourceImpl(get()) }
        single<SearchTVShowDatasource> { SearchTVShowDatasourceImpl(get()) }
        single<TVShowDetailsDatasource> { TVShowDetailsDatasourceImpl(get()) }
        single<PeopleDatasource> { PeopleDatasourceImpl(get()) }
        single<PersonDetailsDatasource> { PersonDetailsDatasourceImpl(get()) }
    }
}