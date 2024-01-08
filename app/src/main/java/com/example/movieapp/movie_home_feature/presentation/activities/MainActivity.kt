package com.example.movieapp.movie_home_feature.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.movieapp.movie_home_feature.presentation.components.MainScreen
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel
import com.example.movieapp.movie_home_feature.presentation.viewmodel.MoviesViewModel
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val tvViewModel: TvViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                MainScreen(
                    viewModel = viewModel,
                    moviesViewModel = moviesViewModel,
                    tvViewModel = tvViewModel
                )
            }
        }
    }
}