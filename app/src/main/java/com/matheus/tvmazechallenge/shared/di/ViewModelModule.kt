package com.matheus.tvmazechallenge.shared.di

import com.matheus.tvmazechallenge.features.tvmazeshows.viewmodel.TVMazeShowsViewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        single { TVMazeShowsViewModel(get()) }
    }
}