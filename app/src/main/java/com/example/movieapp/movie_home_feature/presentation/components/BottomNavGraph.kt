package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
            MoviesCategoriesScreen(navController = navController)
        }
        composable(BottomBarScreen.Tv.route) {
            TvCategoriesScreen(navController = navController)
        }
        composable(BottomBarScreen.Search.route) {
            SearchScreen()
        }
        composable(
            route = "movies_category_screen/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId")
            categoryId?.let {
                MoviesCategoryScreen(categoryId = it)
            }
        }
        composable(
            route = "tv_category_screen/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId")
            categoryId?.let {
                TvCategoryScreen(categoryId = it)
            }
        }
    }
}
