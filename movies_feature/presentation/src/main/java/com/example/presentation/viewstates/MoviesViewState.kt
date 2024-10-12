package com.example.presentation.viewstates


import com.example.domain.models.CategoriesResponse
import com.example.domain.models.TrendingMoviesResponse

sealed class MoviesViewState {
    object Loading : MoviesViewState()
    data class SuccessMoviesCategories(val categories: CategoriesResponse?) : MoviesViewState()
    data class SuccessMovieCategory(val category: TrendingMoviesResponse?) : MoviesViewState()
    data class Error(val message: String) : MoviesViewState()
}
