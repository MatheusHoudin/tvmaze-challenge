package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.tvmazeshows.repository.shows.TVMazeShowRepositoryImpl
import com.matheus.tvmazechallenge.features.tvmazeshows.repository.shows.TVMazeShowsRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single<TVMazeShowsRepository> { TVMazeShowRepositoryImpl(get()) }
    }
}