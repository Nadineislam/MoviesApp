package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.core.extensions.onBottomReached
import com.example.movieapp.movie_home_feature.presentation.intents.MoviesIntent
import com.example.movieapp.movie_home_feature.presentation.viewmodel.MoviesViewModel

@Composable
fun MoviesCategoryScreen(viewModel: MoviesViewModel = hiltViewModel(), categoryId: Int) {
    LaunchedEffect(categoryId) {
        viewModel.processIntent(
            MoviesIntent.FetchMovieCategory(page = 1, categoryId = categoryId)
        )
    }

    val movieCategoriesState by viewModel.moviesState.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GetMoviesResourceList(
            state = movieCategoriesState,
            emptyListMessage = "Error fetching movies"
        ) { resource ->
            val movieList = resource?.trendingMovies
            movieList?.let { movies ->
                LazyVerticalGrid(
                    state = lazyGridState,
                    columns = GridCells.Adaptive(150.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(movies.size) { index ->
                        val movie = movies[index]
                        MovieItem(movie = movie)

                        lazyGridState.onBottomReached(buffer = 5) {
                            viewModel.processIntent(
                                MoviesIntent.FetchMovieCategory(
                                    categoryId = categoryId,
                                    page = viewModel.currentPage
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}