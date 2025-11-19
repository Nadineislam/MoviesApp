package com.example.movie_feature.presentation.components


import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screens(val route: String) {
    object MoviesCategory : Screens(route = "movies_category_screen/{categoryId}") {
        fun createRoute(categoryId: Int) = "movies_category_screen/$categoryId"
    }

     object MovieDetails : Screens(route = "movie_details_screen/{movieName}/{moviePoster}/{movieOverview}/{movieVote}") {
        fun createRoute(
            movieName: String,
            moviePoster: String,
            movieOverview: String,
            movieVote: String
        ): String {
            val encodedName = URLEncoder.encode(movieName, StandardCharsets.UTF_8.toString())
            val encodedPoster = URLEncoder.encode(moviePoster, StandardCharsets.UTF_8.toString())
            val encodedOverview = URLEncoder.encode(movieOverview, StandardCharsets.UTF_8.toString())
            val encodedVote = URLEncoder.encode(movieVote, StandardCharsets.UTF_8.toString())
            return "movie_details_screen/$encodedName/$encodedPoster/$encodedOverview/$encodedVote"
        }
    }
    object TvCategory : Screens(route = "tv_category_screen/{categoryId}") {
        fun createRoute(categoryId: Int) = "tv_category_screen/$categoryId"
    }

    object PeopleDetails : Screens(route = "people_details_screen/{personName}/{personPoster}") {
        fun createRoute(personName: String, personPoster: String): String {
            val encodedName = URLEncoder.encode(personName, StandardCharsets.UTF_8.toString())
            val encodedPoster = URLEncoder.encode(personPoster, StandardCharsets.UTF_8.toString())
            return "people_details_screen/$encodedName/$encodedPoster"
        }
    }
}
