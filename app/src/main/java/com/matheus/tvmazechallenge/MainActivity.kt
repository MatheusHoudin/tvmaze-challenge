package com.matheus.tvmazechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.matheus.tvmazechallenge.databinding.ActivityMainBinding
import com.matheus.tvmazechallenge.features.tvshows.ui.TVShowPage
import com.matheus.tvmazechallenge.features.tvshows.viewmodel.TVShowsViewModel
import com.matheus.tvmazechallenge.shared.extensions.isGone
import com.matheus.tvmazechallenge.shared.extensions.isVisible
import com.matheus.tvmazechallenge.shared.util.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TVShowsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TVMazeChallenge()
        }
    }

    private fun configureBottomNavigationVisibilityListener(binding: ActivityMainBinding) {
        findNavController(R.id.nav_host).addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.tvShowDetailsFragment) {
                binding.bottomNavigation.isGone()
                return@addOnDestinationChangedListener
            }

            binding.bottomNavigation.isVisible()
        }
    }

    @Composable
    private fun TVMazeChallenge() {
        val firaSans = FontFamily(
            Font(R.font.firasans_regular),
            Font(R.font.firasans_semibold, FontWeight.SemiBold),
            Font(R.font.firasans_bold, FontWeight.Bold)
        )
        MaterialTheme(
            typography = Typography(
                body1 = TextStyle(
                    fontFamily = firaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                subtitle1 = TextStyle(
                    fontFamily = firaSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        ) {
            val navController = rememberNavController()
            Scaffold(
                backgroundColor = AppColors.tvMazeMainColor
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "TVShowPage"
                ) {
                    composable("TVShowPage") { TVShowPage(viewModel) }
                }
            }
        }
    }

}