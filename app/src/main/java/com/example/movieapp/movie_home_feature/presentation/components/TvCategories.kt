package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@Composable
fun MoviesCategoriesScreen(viewModel: TvViewModel) {
    GetTvCategories(viewModel = viewModel)

}

@Composable
fun GetTvCategories(viewModel: TvViewModel) {
    val categoriesState by viewModel.state.collectAsStateWithLifecycle()

    GetTvResourceList2(
        state = categoriesState,
        emptyListMessage = "Error fetching categories",
        onSuccessCategories = { categories ->
            Categories(categories = categories?.categoriesList ?: emptyList())
        }
    )

}