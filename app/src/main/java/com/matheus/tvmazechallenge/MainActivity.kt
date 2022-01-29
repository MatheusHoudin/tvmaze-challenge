package com.matheus.tvmazechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matheus.tvmazechallenge.shared.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainActivity)
            modules(listOf(AppModule.appModule))
        }

        setContentView(R.layout.activity_main)
    }
}