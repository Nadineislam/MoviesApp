package com.example.movieapp.movie_home_feature.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.core.utils.Constants.Companion.CATEGORY_ID
import com.example.movieapp.movie_home_feature.presentation.components.GetResourceList
import com.example.movieapp.movie_home_feature.presentation.activities.ui.theme.MovieAppTheme
import com.example.movieapp.movie_home_feature.presentation.components.TvItem
import com.example.movieapp.movie_home_feature.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesCategory : ComponentActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private var categoryId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        categoryId = intent.getIntExtra(CATEGORY_ID, 0)
        moviesViewModel.getMovieCategories(categoryId ?: 0)
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesCategoryScreen(viewModel = moviesViewModel)
                }
            }
        }
    }
}

@Composable
fun MoviesCategoryScreen(viewModel: MoviesViewModel) {
    val movieCategoriesState = viewModel.movieCategories.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GetResourceList(
            resourceState = movieCategoriesState.value,
            emptyListMessage = "Error fetching movies"
        ) { resource ->
            val movieList = resource?.trendingMovies
            movieList?.let { movies ->
                LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
                    items(movies) { movie ->
                        TvItem(movie = movie)
                    }
                }
            }
        }
    }
}


