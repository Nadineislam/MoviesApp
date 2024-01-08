package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel
import com.example.movieapp.movie_home_feature.presentation.viewmodel.MoviesViewModel
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@Composable
fun BottomNavGraph(
    viewModel: HomeViewModel,
    navController: NavHostController,
    moviesViewModel: MoviesViewModel,
    tvViewModel: TvViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(BottomBarScreen.Movies.route) {
            MoviesCategoriesScreen(moviesViewModel)
        }
        composable(BottomBarScreen.Tv.route) {
            MoviesCategoriesScreen(tvViewModel)
        }
        composable("Search") {
            SearchScreen(viewModel)
        }
    }
}