package com.example.movie_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

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
        composable<MoviesCategoryScreen> { backStackEntry ->
            val args=backStackEntry.toRoute<MoviesCategoryScreen>()
                MoviesCategoryScreen(categoryId = args.categoryId, navController = navController)
        }
        composable<TvCategoryScreen> { backStackEntry ->
            val args=backStackEntry.toRoute<TvCategoryScreen>()
            TvCategoryScreen(categoryId = args.categoryId, navController = navController)
        }
        composable<MovieDetailsScreen> { entry ->
            val args = entry.toRoute<MovieDetailsScreen>()
            MovieDetailScreen(
                movieName = args.movieName,
                moviePoster = args.moviePoster,
                movieOverview = args.movieOverview,
                movieVote = args.movieVote
            )

        }
        composable<PeopleDetailsScreen> { entry ->
            val args = entry.toRoute<PeopleDetailsScreen>()
            PersonDetailsScreen(
                personName = args.personName,
                personPoster = args.personPoster
            )
        }
    }
}
