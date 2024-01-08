package com.example.movieapp.movie_home_feature.presentation.components

import android.content.Intent
import android.widget.Toast
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
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.Tv
import com.example.movieapp.movie_home_feature.presentation.activities.MovieDetails
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@Composable
fun TvCategoryScreen(viewModel: TvViewModel) {
    val searchResult = viewModel.tvCategory.collectAsStateWithLifecycle()

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
                val movieList = resource.data?.trendingTv
                movieList?.let { tv ->
                    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
                        items(tv) { tv ->
                            TvItem(tv = tv)

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TvItem(tv: Tv) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .clickable {
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra(Constants.MOVIE_OVERVIEW, tv.overView)
                intent.putExtra(Constants.MOVIE_NAME, tv.name)
                intent.putExtra(Constants.MOVIE_POSTER, tv.poster)
                intent.putExtra(Constants.MOVIE_VOTE, tv.voteAverage)
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
                model = Constants.IMAGE_BASE_URL + Constants.PIC_POSTER_PATH + tv.poster
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
                text = tv.name,
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