package com.matheus.tvmazechallenge.shared.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import com.matheus.tvmazechallenge.shared.Constants.API_BASE_URL
import com.matheus.tvmazechallenge.shared.Constants.NETWORK_TIMEOUT
import com.matheus.tvmazechallenge.shared.api.TVShowsService
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val networkModule = module {
        single { provideOkHttpInstance() }
        single { provideRetrofitInstance(get()) }
        single { provideTVMazeShowsService(get()) }
    }

    private fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun provideOkHttpInstance(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .callTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private fun provideTVMazeShowsService(retrofit: Retrofit): TVShowsService =
        retrofit.create(TVShowsService::class.java)
}