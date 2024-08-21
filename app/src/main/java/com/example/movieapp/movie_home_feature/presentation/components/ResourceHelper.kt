package com.example.movieapp.movie_home_feature.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.presentation.viewstates.MoviesViewState
import com.example.movieapp.movie_home_feature.presentation.viewstates.TvViewState

@Composable
fun <T> GetResourceList(
    resourceState: Resource<T>,
    emptyListMessage: String,
    successBlock: @Composable (T?) -> Unit
) {
    when (resourceState) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val items = resourceState.data
            successBlock(items)
        }

        is Resource.Error -> {
            val message = resourceState.message ?: emptyListMessage
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
                .show()
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
