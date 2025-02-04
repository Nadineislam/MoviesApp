//package com.example.presentation.viewstates
//
//
//import com.example.domain.models.TrendingMoviesResponse
//import com.example.domain.models.TrendingPeopleResponse
//import com.example.domain.models.TrendingTvResponse
//
//sealed class HomeViewState{
//    object Loading : HomeViewState()
//    data class Success(val movies: TrendingMoviesResponse?, val tvShows: TrendingTvResponse?, val people: TrendingPeopleResponse?) : HomeViewState()
//    data class SuccessMovies(val movies: TrendingMoviesResponse?) : HomeViewState()
//    data class SuccessTvShows(val tvShows: TrendingTvResponse?) : HomeViewState()
//    data class SuccessPeople(val people: TrendingPeopleResponse?) : HomeViewState()
//    data class SuccessSearchResults(val searchResults: TrendingTvResponse?) : HomeViewState()
//    data class Error(val message: String) : HomeViewState()
//}
