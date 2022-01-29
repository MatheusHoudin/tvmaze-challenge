package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.tvmazeshows.datasource.shows.TVMazeShowsDatasource
import com.matheus.tvmazechallenge.features.tvmazeshows.datasource.shows.TVMazeShowsDatasourceImpl
import org.koin.dsl.module

object DatasourceModule {
    val datasourceModule = module {
        single<TVMazeShowsDatasource> { TVMazeShowsDatasourceImpl(get()) }
    }
}