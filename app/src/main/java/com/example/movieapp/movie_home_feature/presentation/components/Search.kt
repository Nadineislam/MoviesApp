package com.example.movieapp.movie_home_feature.presentation.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.core.utils.Constants
import com.example.movieapp.core.utils.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.core.utils.Constants.Companion.PIC_POSTER_PATH
import com.example.movieapp.movie_home_feature.data.remote.dto.Tv
import com.example.movieapp.movie_home_feature.presentation.activities.MovieDetails
import com.example.movieapp.movie_home_feature.presentation.intents.HomeIntent
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: HomeViewModel= hiltViewModel()) {
    val searchQuery = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery.value,
            onValueChange = { query ->
                searchQuery.value = query
                viewModel.processIntent(HomeIntent.SearchMovies(query))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = "Search Movies..") }
        )

        val homeState by viewModel.homeState.collectAsStateWithLifecycle()

        GetHomeResourceList(
            state = homeState,
            emptyListMessage = "Error fetching movies",
            onSuccessSearchResults = { searchResults ->
                val movieList = searchResults?.trendingTv
                movieList?.let { movies ->
                    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
                        items(movies) { movie ->
                            MovieItem(movie = movie)
                        }
                    }
                }
            }
        )
    }
}


@Composable
fun MovieItem(movie: Tv) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .clickable {
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra(Constants.MOVIE_OVERVIEW, movie.overView)
                intent.putExtra(Constants.MOVIE_NAME, movie.name)
                intent.putExtra(Constants.MOVIE_POSTER, movie.poster)
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
                model = IMAGE_BASE_URL + PIC_POSTER_PATH + movie.poster
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