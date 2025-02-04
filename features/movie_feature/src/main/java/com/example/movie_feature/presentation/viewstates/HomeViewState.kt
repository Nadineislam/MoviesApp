package com.example.movie_feature.presentation.viewstates

import com.example.movie_feature.domain.models.TrendingMoviesResponse
import com.example.movie_feature.domain.models.TrendingPeopleResponse
import com.example.movie_feature.domain.models.TrendingTvResponse

sealed interface HomeViewState {
    object Loading : HomeViewState
    data class SuccessTrendingData(
        val movies: TrendingMoviesResponse?=null,
        val tvShows: TrendingTvResponse?=null,
        val people: TrendingPeopleResponse?=null
    ) : HomeViewState

    data class SuccessSearchResults(val searchResults: TrendingTvResponse?) : HomeViewState
}
