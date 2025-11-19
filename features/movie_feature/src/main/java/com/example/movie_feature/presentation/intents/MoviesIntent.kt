package com.example.movie_feature.presentation.intents

sealed interface MoviesIntent {
    object FetchMoviesCategories : MoviesIntent
    data class FetchMovieCategory(val page: Int, val categoryId: Int) : MoviesIntent
}
