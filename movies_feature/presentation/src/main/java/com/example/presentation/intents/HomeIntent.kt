package com.example.presentation.intents

sealed class HomeIntent{
    object LoadTrendingMovies : HomeIntent()
    object LoadTrendingTvShows : HomeIntent()
    object LoadTrendingPeople : HomeIntent()
    data class SearchMovies(val query: String) : HomeIntent()
    object LoadAllTrendingData : HomeIntent()

}
