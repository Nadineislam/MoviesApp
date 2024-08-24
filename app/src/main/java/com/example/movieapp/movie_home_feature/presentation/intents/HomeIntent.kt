package com.example.movieapp.movie_home_feature.presentation.intents

sealed class HomeIntent{
    object LoadTrendingMovies : HomeIntent()
    object LoadTrendingTvShows : HomeIntent()
    object LoadTrendingPeople : HomeIntent()
    data class SearchMovies(val query: String) : HomeIntent()
    object LoadAllTrendingData : HomeIntent() // New Intent

}
