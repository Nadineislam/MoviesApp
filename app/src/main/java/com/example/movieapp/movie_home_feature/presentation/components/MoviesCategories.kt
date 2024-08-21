package com.example.movieapp.movie_home_feature.presentation.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.core.utils.Constants.Companion.CATEGORY_ID
import com.example.movieapp.movie_home_feature.data.remote.dto.Categories
import com.example.movieapp.movie_home_feature.presentation.activities.MoviesCategory
import com.example.movieapp.movie_home_feature.presentation.viewmodel.MoviesViewModel

@Composable
fun MoviesCategoriesScreen(viewModel: MoviesViewModel) {
    GetMoviesCategories(viewModel = viewModel)

}

@Composable
fun GetMoviesCategories(viewModel: MoviesViewModel) {
    val categoriesMovieState by viewModel.moviesState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    GetMoviesResourceList(
        state = categoriesMovieState,
        emptyListMessage = "Error fetching movies",
        onSuccessCategories = { categories ->
            Categories(
                categories = categories?.categoriesList ?: emptyList(),
                navigateToCategory = { categoryId ->
                    val intent = Intent(context, MoviesCategory::class.java)
                    intent.putExtra(CATEGORY_ID, categoryId)
                    context.startActivity(intent)
                }
            )
        }
    )
}

@Composable
fun Categories(
    categories: List<Categories>,
    navigateToCategory: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(categories) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Transparent)
                        .height(80.dp)
                        .clickable {
                            navigateToCategory(category.id)
                        }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .background(Color.White)
                    ) {
                        Text(
                            text = category.name,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }
            }
        }
    }
}
