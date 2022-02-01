package com.matheus.tvmazechallenge.shared.di

import android.content.Context
import androidx.room.Room
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.dao.AppDatabase
import com.matheus.tvmazechallenge.shared.dao.TVShowDatabaseDAO
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocalDatabaseModule {
    val localDatabaseModule = module {
        single { provideDatabase(androidContext()) }
        single { provideTVShowDatabaseDAO(get()) }
    }

    private fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()

    private fun provideTVShowDatabaseDAO(appDatabase: AppDatabase): TVShowDatabaseDAO =
        appDatabase.tvShowDatabaseDao()
}