package com.example.movieapp.movie_home_feature.presentation.components

import com.example.movieapp.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Movies : BottomBarScreen(
        route = "movies",
        title = "Movies",
        icon = R.drawable.ic_movie
    )

    object Tv : BottomBarScreen(
        route = "tv",
        title = "TV",
        icon = R.drawable.ic_tv
    )
}
