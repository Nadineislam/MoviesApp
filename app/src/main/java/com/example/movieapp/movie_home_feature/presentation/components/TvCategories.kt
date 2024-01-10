package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.core.utils.GetResourceList
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@Composable
fun MoviesCategoriesScreen(viewModel: TvViewModel) {
    GetTvCategories(viewModel = viewModel)

}

@Composable
fun GetTvCategories(viewModel: TvViewModel) {
    val categoriesMovieState by viewModel.categories.collectAsStateWithLifecycle()
    GetResourceList(
        resourceState = categoriesMovieState,
        emptyListMessage = "Error fetching movies"
    ) { categories ->
        Categories(categories = categories?.categoriesList ?: emptyList())
    }
}