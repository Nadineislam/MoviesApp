package com.example.movie_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movie_feature.domain.models.CategoriesResponse
import com.example.movie_feature.domain.models.TrendingMoviesResponse
import com.example.movie_feature.domain.models.TrendingPeopleResponse
import com.example.movie_feature.domain.models.TrendingTvResponse
import com.example.movie_feature.presentation.viewmodel.HomeViewModel
import com.example.movie_feature.presentation.viewstates.HomeViewState
import com.example.movie_feature.presentation.viewstates.MoviesViewState
import com.example.movie_feature.presentation.viewstates.TvViewState
import com.example.presentation.StateRenderer

@Composable
fun GetHomeResourceList(
    viewModel: HomeViewModel = hiltViewModel(),
    state: StateRenderer<HomeViewState, Any>,
    onSuccessMovies: @Composable (TrendingMoviesResponse?) -> Unit = {},
    onSuccessTvShows: @Composable (TrendingTvResponse?) -> Unit = {},
    onSuccessPeople: @Composable (TrendingPeopleResponse?) -> Unit = {},
    onSuccessSearchResults: @Composable (TrendingTvResponse?) -> Unit = {}
) {
    StateRenderer.of(statRenderer = state, retryAction = { viewModel.loadAllTrendingData() }) {

        onSuccessState { output ->
            when (output) {
                is HomeViewState.SuccessTrendingData -> {
                    onSuccessMovies(output.movies)
                    onSuccessTvShows(output.tvShows)
                    onSuccessPeople(output.people)
                }

                is HomeViewState.SuccessSearchResults -> {
                    onSuccessSearchResults(output.searchResults)
                }
            }
        }
    }
}


@Composable
fun GetTvResourceList(
    state: StateRenderer<TvViewState, Any>,
    onSuccessCategories: @Composable (CategoriesResponse?) -> Unit = {},
    onSuccessCategory: @Composable (TrendingTvResponse?) -> Unit = {}
) {
    StateRenderer.of(statRenderer = state, retryAction = { }) {

        onSuccessState { output ->
            when (output) {
                is TvViewState.SuccessTvCategories -> {
                    onSuccessCategories(output.categories)
                }

                is TvViewState.SuccessTvCategory -> {
                    onSuccessCategory(output.tvCategories)
                }
            }
        }
    }
}

@Composable
fun GetMoviesResourceList(
    state: StateRenderer<MoviesViewState, Any>,
    onSuccessCategories: @Composable (CategoriesResponse?) -> Unit = {},
    onSuccessCategory: @Composable (TrendingMoviesResponse?) -> Unit = {}
) {
    StateRenderer.of(statRenderer = state, retryAction = { }) {

        onSuccessState { output ->
            when (output) {
                is MoviesViewState.SuccessMovieCategory -> {
                    onSuccessCategory(output.category)
                }

                is MoviesViewState.SuccessMoviesCategories -> {
                    onSuccessCategories(output.categories)
                }
            }
        }
    }
}
