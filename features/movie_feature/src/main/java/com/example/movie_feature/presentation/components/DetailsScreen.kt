package com.example.movie_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.utils.Constants

@Composable
fun MovieDetailsScreen(
    movieName: String,
    moviePoster: String,
    movieOverview: String,
    movieVote: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = Constants.IMAGE_BASE_URL + Constants.PIC_POSTER_PATH + moviePoster,
            contentDescription = "movie",
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp)
                .height(400.dp)
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = movieName, fontWeight = FontWeight.Bold, modifier = Modifier.padding(15.dp))
            Row(modifier = Modifier.padding(15.dp)) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    tint = Color.Yellow,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    text = movieVote,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
        Text(
            text = movieOverview, modifier = Modifier
                .padding(vertical = 12.dp)
                .padding(15.dp)
        )


    }
}

@Composable
fun PersonDetailsScreen(
    personName: String,
    personPoster: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = Constants.IMAGE_BASE_URL + Constants.PIC_POSTER_PATH + personPoster,
            contentDescription = "person",
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp)
                .height(400.dp)
        )
        Text(text = personName, fontWeight = FontWeight.Bold, modifier = Modifier.padding(15.dp))


    }
}

