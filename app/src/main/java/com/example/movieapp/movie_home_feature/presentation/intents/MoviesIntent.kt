package com.example.movieapp.movie_home_feature.presentation.intents

sealed class MoviesIntent {
    object FetchMoviesCategories : MoviesIntent()
    data class FetchMovieCategory(val page: Int, val categoryId: Int) : MoviesIntent()
}
