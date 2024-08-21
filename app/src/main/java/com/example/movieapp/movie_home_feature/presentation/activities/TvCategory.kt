package com.example.movieapp.movie_home_feature.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.core.extensions.onBottomReached
import com.example.movieapp.core.utils.Constants
import com.example.movieapp.movie_home_feature.presentation.activities.ui.theme.MovieAppTheme
import com.example.movieapp.movie_home_feature.presentation.components.GetTvResourceList
import com.example.movieapp.movie_home_feature.presentation.components.MovieItem
import com.example.movieapp.movie_home_feature.presentation.intents.TvIntent
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvCategory : ComponentActivity() {
    private val tvViewModel: TvViewModel by viewModels()
    private var categoryId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        categoryId = intent.getIntExtra(Constants.CATEGORY_ID, 0)
        tvViewModel.processIntent(TvIntent.FetchTvCategory(1,categoryId ?: 0))
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TvCategoryScreen(viewModel = tvViewModel)
                }
            }
        }
    }
}

@Composable
fun TvCategoryScreen(viewModel: TvViewModel) {
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
