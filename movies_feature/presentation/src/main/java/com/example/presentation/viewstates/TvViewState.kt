package com.example.presentation.viewstates

import com.example.domain.models.CategoriesResponse
import com.example.domain.models.TrendingTvResponse

sealed class TvViewState{
    object Loading: TvViewState()
    data class SuccessTvCategories(val categories: CategoriesResponse?) : TvViewState()
    data class SuccessTvCategory(val tvCategories: TrendingTvResponse?) : TvViewState()
    data class Error(val message: String): TvViewState()
}
