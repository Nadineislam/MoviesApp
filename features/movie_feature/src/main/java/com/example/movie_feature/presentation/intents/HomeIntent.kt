package com.example.movie_feature.presentation.intents

sealed interface HomeIntent{
    data class SearchMovies(val query: String) : HomeIntent
    object LoadAllTrendingData : HomeIntent
}
