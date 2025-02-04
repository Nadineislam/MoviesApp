package com.example.movie_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

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
            SearchScreen(navController = navController)
        }
        composable(
            route = "movies_category_screen/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId")
            categoryId?.let {
                MoviesCategoryScreen(categoryId = it, navController = navController)
            }
        }
        composable(
            route = "tv_category_screen/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId")
            categoryId?.let {
                TvCategoryScreen(categoryId = it, navController = navController)
            }
        }
        composable(
            route = "movie_details_screen/{movieName}/{moviePoster}/{movieOverview}/{movieVote}",
            arguments = listOf(
                navArgument("movieName") { type = NavType.StringType },
                navArgument("moviePoster") { type = NavType.StringType },
                navArgument("movieOverview") { type = NavType.StringType },
                navArgument("movieVote") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val movieName = URLDecoder.decode(
                backStackEntry.arguments?.getString("movieName"),
                StandardCharsets.UTF_8.toString()
            )
            val moviePoster = URLDecoder.decode(
                backStackEntry.arguments?.getString("moviePoster"),
                StandardCharsets.UTF_8.toString()
            )
            val movieOverview = URLDecoder.decode(
                backStackEntry.arguments?.getString("movieOverview"),
                StandardCharsets.UTF_8.toString()
            )
            val movieVote = URLDecoder.decode(
                backStackEntry.arguments?.getString("movieVote"),
                StandardCharsets.UTF_8.toString()
            )

            MovieDetailsScreen(
                movieName = movieName,
                moviePoster = moviePoster,
                movieOverview = movieOverview,
                movieVote = movieVote
            )
        }
        composable(
            route = "people_details_screen/{personName}/{personPoster}",
            arguments = listOf(
                navArgument("personName") { type = NavType.StringType },
                navArgument("personPoster") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val personName = URLDecoder.decode(
                backStackEntry.arguments?.getString("personName"),
                StandardCharsets.UTF_8.toString()
            )
            val personPoster = URLDecoder.decode(
                backStackEntry.arguments?.getString("personPoster"),
                StandardCharsets.UTF_8.toString()
            )

            PersonDetailsScreen(personName = personName, personPoster = personPoster)
        }
    }
}
