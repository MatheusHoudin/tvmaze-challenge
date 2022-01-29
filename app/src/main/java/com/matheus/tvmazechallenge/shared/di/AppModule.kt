package com.matheus.tvmazechallenge.shared.di

object AppModule {
    val appModule =
        NetworkModule.networkModule +
                DatasourceModule.datasourceModule +
                RepositoryModule.repositoryModule +
                ViewModelModule.viewModelModule
}