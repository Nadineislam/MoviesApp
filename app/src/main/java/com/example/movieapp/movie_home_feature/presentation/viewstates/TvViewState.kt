package com.example.movieapp.movie_home_feature.presentation.viewstates

import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse

sealed class TvViewState{
    object Loading: TvViewState()
    data class SuccessTvCategories(val categories: CategoriesResponse?) : TvViewState()
    data class SuccessTvCategory(val tvCategories: TrendingTvResponse?) : TvViewState()
    data class Error(val message: String): TvViewState()
}
