package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepository
import com.matheus.tvmazechallenge.features.search.repository.SearchTVShowRepositoryImpl
import com.matheus.tvmazechallenge.features.tvmazeshows.repository.TVMazeShowRepositoryImpl
import com.matheus.tvmazechallenge.features.tvmazeshows.repository.TVMazeShowsRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single<TVMazeShowsRepository> { TVMazeShowRepositoryImpl(get()) }
        single<SearchTVShowRepository> { SearchTVShowRepositoryImpl(get()) }
    }
}