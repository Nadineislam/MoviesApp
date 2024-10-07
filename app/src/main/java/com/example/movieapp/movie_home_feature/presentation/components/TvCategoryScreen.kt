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
import com.example.movieapp.movie_home_feature.presentation.intents.TvIntent
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@Composable
fun TvCategoryScreen(viewModel: TvViewModel = hiltViewModel(), categoryId: Int) {

    LaunchedEffect(categoryId) {
        viewModel.processIntent(
            TvIntent.FetchTvCategory(page = 1, categoryId = categoryId)
        )
    }
    val tvCategoryState by viewModel.state.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GetTvResourceList(
            state = tvCategoryState,
            emptyListMessage = "Error fetching TV shows",
            onSuccessCategory = { resource ->
                val tvList = resource?.trendingTv

                tvList?.let { tv ->
                    LazyVerticalGrid(
                        state = lazyGridState,
                        columns = GridCells.Adaptive(150.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(tv.size) { index ->
                            val movie = tv[index]
                            MovieItem(movie = movie)

                            lazyGridState.onBottomReached(buffer = 5) {
                                viewModel.processIntent(
                                    TvIntent.FetchTvCategory(
                                        categoryId = viewModel.currentCategory ?: 0,
                                        page = viewModel.currentPage
                                    )
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}
