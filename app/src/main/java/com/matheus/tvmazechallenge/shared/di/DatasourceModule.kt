package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowDatasource
import com.matheus.tvmazechallenge.features.search.datasource.SearchTVShowDatasourceImpl
import com.matheus.tvmazechallenge.features.tvmazeshows.datasource.TVMazeShowsDatasource
import com.matheus.tvmazechallenge.features.tvmazeshows.datasource.TVMazeShowsDatasourceImpl
import org.koin.dsl.module

object DatasourceModule {
    val datasourceModule = module {
        single<TVMazeShowsDatasource> { TVMazeShowsDatasourceImpl(get()) }
        single<SearchTVShowDatasource> { SearchTVShowDatasourceImpl(get()) }
    }
}