package com.example.movieapp.movie_home_feature.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@Composable
fun MoviesCategoriesScreen(viewModel: TvViewModel) {
    GetTvCategories(viewModel = viewModel)

}

@Composable
fun GetTvCategories(viewModel: TvViewModel) {
    val categoriesMovie by viewModel.categories.collectAsStateWithLifecycle()
    when (val resource = categoriesMovie) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val categories = resource.data?.categoriesList
            Categories(categories = categories ?: emptyList())
        }

        is Resource.Error -> {
            val message = resource.message ?: "Error fetching meal"
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
                .show()
        }
    }
}