package com.example.movie_feature.presentation.viewstates

import com.example.movie_feature.domain.models.CategoriesResponse
import com.example.movie_feature.domain.models.TrendingMoviesResponse

sealed class MoviesViewState {
    object Loading : MoviesViewState()
    data class SuccessMoviesCategories(val categories: CategoriesResponse?) : MoviesViewState()
    data class SuccessMovieCategory(val category: TrendingMoviesResponse?) : MoviesViewState()
}
