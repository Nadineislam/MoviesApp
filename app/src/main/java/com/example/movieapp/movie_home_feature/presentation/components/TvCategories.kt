package com.example.movieapp.movie_home_feature.presentation.components

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.core.utils.Constants.Companion.CATEGORY_ID
import com.example.movieapp.movie_home_feature.presentation.activities.TvCategory
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@Composable
fun MoviesCategoriesScreen(viewModel: TvViewModel) {
    GetTvCategories(viewModel = viewModel)

}

@Composable
fun GetTvCategories(viewModel: TvViewModel) {
    val categoriesState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    GetTvResourceList(
        state = categoriesState,
        emptyListMessage = "Error fetching categories",
        onSuccessCategories = { categories ->
            Categories(
                categories = categories?.categoriesList ?: emptyList(),
                navigateToCategory = { categoryId ->
                    val intent = Intent(context, TvCategory::class.java)
                    intent.putExtra(CATEGORY_ID, categoryId)
                    context.startActivity(intent)
                }
            )
        }
    )
}