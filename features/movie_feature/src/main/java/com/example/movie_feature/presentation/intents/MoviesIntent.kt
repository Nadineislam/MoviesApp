package com.example.movie_feature.presentation.intents

sealed class MoviesIntent {
    object FetchMoviesCategories : MoviesIntent()
    data class FetchMovieCategory(val page: Int, val categoryId: Int) : MoviesIntent()
}
