package com.example.movie_feature.presentation.viewstates

import com.example.movie_feature.domain.models.CategoriesResponse
import com.example.movie_feature.domain.models.TrendingTvResponse

sealed interface TvViewState{
    object Loading: TvViewState
    data class SuccessTvCategories(val categories: CategoriesResponse?) : TvViewState
    data class SuccessTvCategory(val tvCategories: TrendingTvResponse?) : TvViewState
}
