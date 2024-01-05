package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel

@Composable
fun BottomNavGraph(
    viewModel: HomeViewModel,
    navController: NavHostController,
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

        }
        composable(BottomBarScreen.Tv.route) {

        }
        composable("Search") {
            SearchScreen(viewModel)
        }
    }
}