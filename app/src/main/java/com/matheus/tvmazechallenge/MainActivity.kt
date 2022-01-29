package com.matheus.tvmazechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matheus.tvmazechallenge.features.tvmazeshows.viewmodel.TVMazeShowsViewModel
import com.matheus.tvmazechallenge.shared.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainActivity : AppCompatActivity() {
    private val viewModel: TVMazeShowsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainActivity)
            modules(AppModule.appModule)
        }

        viewModel.fetchShows()

        setContentView(R.layout.activity_main)
    }
}