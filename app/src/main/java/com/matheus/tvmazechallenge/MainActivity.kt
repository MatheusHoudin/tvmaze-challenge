package com.matheus.tvmazechallenge

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.gson.Gson
import com.matheus.tvmazechallenge.features.favorites.ui.FavoriteShowsPage
import com.matheus.tvmazechallenge.features.favorites.viewmodel.FavoriteTVShowsViewModel
import com.matheus.tvmazechallenge.features.people.ui.PeoplePage
import com.matheus.tvmazechallenge.features.persondetails.ui.PersonDetailsActivity
import com.matheus.tvmazechallenge.features.search.ui.SearchShowsPage
import com.matheus.tvmazechallenge.features.tvshowdetails.ui.TVShowDetailsPage
import com.matheus.tvmazechallenge.features.tvshowdetails.viewmodel.TVShowDetailsViewModel
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.ui.TVShowPage
import com.matheus.tvmazechallenge.shared.util.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TVMazeChallenge()
        }
    }

    @Composable
    private fun TVMazeChallenge() {
        val context = LocalContext.current
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
                ),
                subtitle2 = TextStyle(
                    fontFamily = firaSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
        ) {
            val navController = rememberNavController()
            Scaffold(
                backgroundColor = AppColors.tvMazeMainColor,
                bottomBar = { TVMazeBottomNavigation(navController) }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "TVShowPage"
                ) {
                    composable("TVShowPage") {
                        TVShowPage {
                            navController.navigate("TVShowDetailsPage/${Uri.encode(Gson().toJson(it))}")
                        }
                    }
                    composable("Search") {
                        SearchShowsPage()
                    }
                    composable("Favorites") {
                        FavoriteShowsPage {
                            navController.navigate("TVShowDetailsPage/${Uri.encode(Gson().toJson(it))}")
                        }
                    }
                    composable("People") {
                        PeoplePage {
                            val intent = Intent(context, PersonDetailsActivity::class.java)
                            intent.putExtra("person", it)
                            context.startActivity(intent)
                        }
                    }
                    composable("TVShowDetailsPage/{tvShowEntity}") {
                        val tvShowEntity = Gson().fromJson(
                            it.arguments?.getString("tvShowEntity"),
                            TVShowEntity::class.java
                        )
                        val viewModel: TVShowDetailsViewModel = hiltViewModel()
                        val favoriteTVShowsViewModel: FavoriteTVShowsViewModel = hiltViewModel()
                        viewModel.fetchTVShowEpisodes(tvShowEntity.id)
                        favoriteTVShowsViewModel.getFavoriteTVShow(tvShowEntity.id)
                        TVShowDetailsPage(
                            tvShowEntity = tvShowEntity,
                            viewModel = viewModel,
                            favoriteTVShowViewModel = favoriteTVShowsViewModel
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun TVMazeBottomNavigation(navController: NavController) {
        val navOptions = listOf(
            BottomNavItem.Home,
            BottomNavItem.MyNetwork,
            BottomNavItem.AddPost,
            BottomNavItem.Notification
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavigation {
            navOptions.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = stringResource(id = item.title)) },
                    selected = currentRoute == item.screenRoute,
                    onClick = {
                        navController.navigate(item.screenRoute)
                    }
                )
            }
        }
    }

    sealed class BottomNavItem(
        @StringRes val title: Int,
        @DrawableRes val icon: Int,
        val screenRoute: String
    ) {
        object Home :
            BottomNavItem(R.string.bottom_navigation_home, R.drawable.ic_home, "TVShowPage")

        object MyNetwork :
            BottomNavItem(R.string.bottom_navigation_search, R.drawable.ic_search, "Search")

        object AddPost :
            BottomNavItem(R.string.bottom_navigation_favorites, R.drawable.ic_favorite, "Favorites")

        object Notification :
            BottomNavItem(R.string.bottom_navigation_people, R.drawable.ic_people, "People")
    }
}