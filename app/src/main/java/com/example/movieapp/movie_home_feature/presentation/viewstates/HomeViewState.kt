package com.example.movieapp.movie_home_feature.presentation.viewstates

import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse

sealed class HomeViewState{
    object Loading : HomeViewState()
    data class Success(val movies: TrendingMoviesResponse?, val tvShows: TrendingTvResponse?, val people: TrendingPeopleResponse?) : HomeViewState()
    data class SuccessMovies(val movies: TrendingMoviesResponse?) : HomeViewState()
    data class SuccessTvShows(val tvShows: TrendingTvResponse?) : HomeViewState()
    data class SuccessPeople(val people: TrendingPeopleResponse?) : HomeViewState()
    data class SuccessSearchResults(val searchResults: TrendingTvResponse?) : HomeViewState()
    data class Error(val message: String) : HomeViewState()
}
