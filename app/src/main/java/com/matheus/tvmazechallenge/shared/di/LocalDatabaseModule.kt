package com.matheus.tvmazechallenge.shared.di

import android.content.Context
import androidx.room.Room
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.dao.AppDatabase
import com.matheus.tvmazechallenge.shared.dao.TVShowDatabaseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideTVShowDatabaseDAO(appDatabase: AppDatabase): TVShowDatabaseDAO =
        appDatabase.tvShowDatabaseDao()
}