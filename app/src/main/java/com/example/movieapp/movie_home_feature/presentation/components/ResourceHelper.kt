package com.example.movieapp.movie_home_feature.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.presentation.viewstates.HomeViewState
import com.example.movieapp.movie_home_feature.presentation.viewstates.MoviesViewState
import com.example.movieapp.movie_home_feature.presentation.viewstates.TvViewState
@Composable
fun GetHomeResourceList(
    state: HomeViewState,
    emptyListMessage: String,
    onSuccessMovies: @Composable (TrendingMoviesResponse?) -> Unit = {},
    onSuccessTvShows: @Composable (TrendingTvResponse?) -> Unit = {},
    onSuccessPeople: @Composable (TrendingPeopleResponse?) -> Unit = {},
    onSuccessSearchResults: @Composable (TrendingTvResponse?) -> Unit = {} // Adjust if search results are not TV related
) {
    when (state) {
        is HomeViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is HomeViewState.SuccessMovies -> {
            onSuccessMovies(state.movies)
        }
        is HomeViewState.SuccessTvShows -> {
            onSuccessTvShows(state.tvShows)
        }
        is HomeViewState.SuccessPeople -> {
            onSuccessPeople(state.people)
        }
        is HomeViewState.SuccessSearchResults -> {
            onSuccessSearchResults(state.searchResults)
        }
        is HomeViewState.Error -> {
            val message = state.message.ifEmpty { emptyListMessage }
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
        }

        is HomeViewState.Success -> {
            state.movies?.let { onSuccessMovies(it) }
            state.tvShows?.let { onSuccessTvShows(it) }
            state.people?.let { onSuccessPeople(it) }
        }
    }
}






@Composable
fun GetTvResourceList(
    state: TvViewState,
    emptyListMessage: String,
    onSuccessCategories: @Composable (CategoriesResponse?) -> Unit = {},
    onSuccessCategory: @Composable (TrendingTvResponse?) -> Unit = {}
) {
    when (state) {
        is TvViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is TvViewState.SuccessTvCategories -> {
            onSuccessCategories(state.categories)
        }

        is TvViewState.SuccessTvCategory -> {
            onSuccessCategory(state.tvCategories)
        }

        is TvViewState.Error -> {
            val message = state.message.ifEmpty { emptyListMessage }
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
        }
    }
}
@Composable
fun GetMoviesResourceList(
    state: MoviesViewState,
    emptyListMessage: String,
    onSuccessCategories: @Composable (CategoriesResponse?) -> Unit = {},
    onSuccessCategory: @Composable (TrendingMoviesResponse?) -> Unit = {}
) {
    when (state) {
        is MoviesViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is MoviesViewState.SuccessMoviesCategories -> {
            onSuccessCategories(state.categories)
        }

        is MoviesViewState.SuccessMovieCategory -> {
            onSuccessCategory(state.category)
        }

        is MoviesViewState.Error -> {
            val message = state.message.ifEmpty { emptyListMessage }
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
        }
    }
}
