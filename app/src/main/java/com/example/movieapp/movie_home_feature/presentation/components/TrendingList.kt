package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.core.utils.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.core.utils.Constants.Companion.PIC_POSTER_PATH
import com.example.movieapp.movie_home_feature.data.remote.dto.Movies
import com.example.movieapp.movie_home_feature.data.remote.dto.People
import com.example.movieapp.movie_home_feature.data.remote.dto.Tv
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun TrendingList(items: List<Any>, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyRow {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight()
                        .width(140.dp)
                        .clickable {
                            when (item) {
                                is People -> {
                                    val encodedName = URLEncoder.encode(
                                        item.name,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedPoster = URLEncoder.encode(
                                        item.poster,
                                        StandardCharsets.UTF_8.toString()
                                    )

                                    navController.navigate(
                                        "people_details_screen/$encodedName/$encodedPoster"
                                    )
                                }

                                is Tv -> {
                                    val encodedName = URLEncoder.encode(
                                        item.name,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedPoster = URLEncoder.encode(
                                        item.poster,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedOverview = URLEncoder.encode(
                                        item.overView,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedVote = URLEncoder.encode(
                                        item.voteAverage.toString(),
                                        StandardCharsets.UTF_8.toString()
                                    )

                                    navController.navigate(
                                        "movie_details_screen/$encodedName/$encodedPoster/$encodedOverview/$encodedVote"
                                    )
                                }

                                is Movies -> {
                                    val encodedName = URLEncoder.encode(
                                        item.name,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedPoster = URLEncoder.encode(
                                        item.posterPath,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedOverview = URLEncoder.encode(
                                        item.overView,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedVote = URLEncoder.encode(
                                        item.voteAverage.toString(),
                                        StandardCharsets.UTF_8.toString()
                                    )

                                    navController.navigate(
                                        "movie_details_screen/$encodedName/$encodedPoster/$encodedOverview/$encodedVote"
                                    )
                                }

                                else -> {
                                }
                            }
                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(Color.White)
                    ) {
                        val posterPath = when (item) {
                            is People -> item.poster
                            is Tv -> item.poster
                            is Movies -> item.posterPath
                            else -> ""
                        }

                        val name = when (item) {
                            is People -> item.name
                            is Tv -> item.name
                            is Movies -> item.name
                            else -> ""
                        }

                        AsyncImage(
                            model = IMAGE_BASE_URL + PIC_POSTER_PATH + posterPath,
                            contentDescription = "item",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = name.uppercase(),
                            color = Color.Black,
                            modifier = Modifier.padding(4.dp),
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }
            }
        }
    }
}