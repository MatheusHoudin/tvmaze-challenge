package com.matheus.tvmazechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.matheus.tvmazechallenge.databinding.ActivityMainBinding
import com.matheus.tvmazechallenge.shared.di.AppModule
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainActivity)
            modules(AppModule.appModule)
        }

        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        bottomNavigation.setupWithNavController(findNavController(R.id.nav_host))
    }
}