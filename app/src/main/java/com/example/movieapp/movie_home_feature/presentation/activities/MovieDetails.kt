package com.example.movieapp.movie_home_feature.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.movieapp.core.utils.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_NAME
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_OVERVIEW
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_POSTER
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_VOTE
import com.example.movieapp.core.utils.Constants.Companion.PIC_POSTER_PATH
import com.example.movieapp.movie_home_feature.presentation.activities.ui.theme.MovieAppTheme


class MovieDetails : ComponentActivity() {
    private lateinit var movieName: String
    private lateinit var moviePoster: String
    private lateinit var movieOverview: String
    private lateinit var movieVote: String
    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = intent
        movieName = intent.getStringExtra(MOVIE_NAME) ?: ""
        moviePoster = intent.getStringExtra(MOVIE_POSTER) ?: ""
        movieOverview = intent.getStringExtra(MOVIE_OVERVIEW) ?: ""
        movieVote = intent.getStringExtra(MOVIE_VOTE) ?: "7.0"
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieDetails(movieName, moviePoster, movieOverview,movieVote)
                }
            }
        }
    }
}

@Composable
fun MovieDetails(
    movieName: String,
    moviePoster: String,
    movieOverview: String,
    movieVote:String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val painter =
            rememberImagePainter(data = IMAGE_BASE_URL + PIC_POSTER_PATH + moviePoster)
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp)
                .height(400.dp)
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = movieName, fontWeight = FontWeight.Bold, modifier = Modifier.padding(15.dp))
            Text(text = movieVote, fontWeight = FontWeight.Bold, modifier = Modifier.padding(15.dp))
        }
        Text(text = movieOverview, modifier = Modifier
            .padding(vertical = 12.dp)
            .padding(15.dp))


    }
}
