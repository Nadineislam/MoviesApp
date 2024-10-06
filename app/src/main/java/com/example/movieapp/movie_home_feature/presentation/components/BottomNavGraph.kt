package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(BottomBarScreen.Movies.route) {
            MoviesCategoriesScreen()
        }
        composable(BottomBarScreen.Tv.route) {
            TvCategoriesScreen()
        }
        composable("Search") {
            SearchScreen()
        }
    }
}