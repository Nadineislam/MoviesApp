package com.example.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.core.utils.Constants
import com.example.domain.models.Movies
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MovieItem(movie: Movies, navController: NavController) {
    val encodedMovieName = URLEncoder.encode(movie.name, StandardCharsets.UTF_8.toString())
    val encodedMoviePoster = URLEncoder.encode(movie.posterPath, StandardCharsets.UTF_8.toString())
    val encodedMovieOverview = URLEncoder.encode(movie.overView, StandardCharsets.UTF_8.toString())
    val encodedMovieVote =
        URLEncoder.encode(movie.voteAverage.toString(), StandardCharsets.UTF_8.toString())
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .clickable {

                navController.navigate(
                    "movie_details_screen/$encodedMovieName/$encodedMoviePoster/$encodedMovieOverview/$encodedMovieVote"
                )
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