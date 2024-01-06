package com.example.movieapp.movie_home_feature.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.core.utils.Constants
import com.example.movieapp.core.utils.Constants.Companion.CATEGORY_ID
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.Movies
import com.example.movieapp.movie_home_feature.presentation.activities.ui.theme.MovieAppTheme
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
    val searchResult = viewModel.movieCategories.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val resource = searchResult.value) {
            is Resource.Error -> {
                val message = resource.message ?: "Error fetching movie"
                Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
                    .show()
            }

            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }

            is Resource.Success -> {
                val movieList = resource.data?.trendingMovies
                movieList?.let { movies ->
                    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
                        items(movies) { movie ->
                            MovieItem(movie = movie)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movies) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .clickable {
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra(Constants.MOVIE_OVERVIEW, movie.overView)
                intent.putExtra(Constants.MOVIE_NAME, movie.name)
                intent.putExtra(Constants.MOVIE_POSTER, movie.posterPath)
                intent.putExtra(Constants.MOVIE_VOTE, movie.voteAverage)
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
        ) {
            val painter = rememberAsyncImagePainter(
                model = Constants.IMAGE_BASE_URL + Constants.PIC_POSTER_PATH + movie.posterPath
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )

            Text(
                text = movie.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = TextStyle(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

