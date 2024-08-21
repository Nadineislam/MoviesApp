package com.example.movieapp.movie_home_feature.presentation.viewstates

import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse

sealed class MoviesViewState {
    object Loading : MoviesViewState()
    data class SuccessMoviesCategories(val categories: CategoriesResponse?) : MoviesViewState()
    data class SuccessMovieCategory(val category: TrendingMoviesResponse?) : MoviesViewState()
    data class Error(val message: String) : MoviesViewState()
}
