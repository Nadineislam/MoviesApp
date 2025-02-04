package com.example.movie_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movie_feature.presentation.viewmodel.TvViewModel

@Composable
fun TvCategoriesScreen(navController: NavController, viewModel: TvViewModel = hiltViewModel()) {
    val categoriesState by viewModel.stateRendererFlow.collectAsStateWithLifecycle()

    GetTvResourceList(
        state = categoriesState,
        onSuccessCategories = { categories ->
            Categories(
                categories = categories?.categoriesList ?: emptyList(),
                navigateToCategory = { categoryId ->
                    navController.navigate("tv_category_screen/$categoryId")

                }
            )
        }
    )
}