package com.example.movieapp.movie_home_feature.presentation.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.core.utils.Constants.Companion.IMAGE_BASE_URL
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_NAME
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_OVERVIEW
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_POSTER
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_VOTE
import com.example.movieapp.core.utils.Constants.Companion.PIC_POSTER_PATH
import com.example.movieapp.core.utils.GetResourceList
import com.example.movieapp.movie_home_feature.data.remote.dto.Movies
import com.example.movieapp.movie_home_feature.data.remote.dto.People
import com.example.movieapp.movie_home_feature.data.remote.dto.Tv
import com.example.movieapp.movie_home_feature.presentation.activities.MovieDetails
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            HomeLayout(navController)
            TitleText(text = "Trending Movies")
            GetTrendingMovies(viewModel = viewModel)
            TitleText(text = "Trending Tv")
            GetTrendingTv(viewModel = viewModel)
            TitleText(text = "Trending People")
            GetTrendingPeople(viewModel = viewModel)

        }
    }
}

@Composable
fun HomeLayout(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Home",
            color = Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(6.dp),
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.width(260.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search", modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
                .clickable { navController.navigate("Search") }
        )
    }
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun GetTrendingMovies(viewModel: HomeViewModel) {
    val trendingMoviesState by viewModel.movies.collectAsStateWithLifecycle()
    GetResourceList(
        resourceState = trendingMoviesState,
        emptyListMessage = "Error fetching movies"
    ) { movies ->
        TrendingMovieList(movies = movies?.trendingMovies ?: emptyList())
    }
}

@Composable
fun TrendingMovieList(movies: List<Movies>) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyRow {
            items(movies) { movie ->
                Card(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight()
                        .width(140.dp)
                        .clickable {
                            val intent = Intent(context, MovieDetails::class.java)
                            intent.putExtra(MOVIE_OVERVIEW, movie.overView)
                            intent.putExtra(MOVIE_NAME, movie.name)
                            intent.putExtra(MOVIE_POSTER, movie.posterPath)
                            intent.putExtra(MOVIE_VOTE, movie.voteAverage)
                            context.startActivity(intent)
                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            Color.White
                        )
                    ) {
                        AsyncImage(
                            model = IMAGE_BASE_URL + PIC_POSTER_PATH + movie.posterPath,
                            contentDescription = "movie"
                        )
                        Text(
                            text = movie.name.uppercase(),
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

@Composable
fun GetTrendingTv(viewModel: HomeViewModel) {
    val trendingTvState by viewModel.tv.collectAsStateWithLifecycle()
    GetResourceList(
        resourceState = trendingTvState,
        emptyListMessage = "Error fetching TV shows"
    ) { tvShows ->
        TrendingTvList(tv = tvShows?.trendingTv ?: emptyList())
    }
}

@Composable
fun TrendingTvList(tv: List<Tv>) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow {
            items(tv) { tv ->
                Card(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight()
                        .width(140.dp)
                        .clickable {
                            val intent = Intent(context, MovieDetails::class.java)
                            intent.putExtra(MOVIE_OVERVIEW, tv.overView)
                            intent.putExtra(MOVIE_NAME, tv.name)
                            intent.putExtra(MOVIE_POSTER, tv.poster)
                            intent.putExtra(MOVIE_VOTE, tv.voteAverage)
                            context.startActivity(intent)
                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            Color.White
                        )
                    ) {
                        AsyncImage(
                            model = IMAGE_BASE_URL + PIC_POSTER_PATH + tv.poster,
                            contentDescription = "tv"
                        )
                        Text(
                            text = tv.name.uppercase(),
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

@Composable
fun GetTrendingPeople(viewModel: HomeViewModel) {
    val trendingPeopleState by viewModel.people.collectAsStateWithLifecycle()
    GetResourceList(
        resourceState = trendingPeopleState,
        emptyListMessage = "Error fetching people"
    ) { people ->
        TrendingPeopleList(people = people?.trendingPeople ?: emptyList())
    }
}

@Composable
fun TrendingPeopleList(people: List<People>) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyRow {
            items(people) { people ->
                Card(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight()
                        .width(140.dp)
                        .clickable {
                            val intent = Intent(context, MovieDetails::class.java)
                            intent.putExtra(MOVIE_NAME, people.name)
                            intent.putExtra(MOVIE_POSTER, people.poster)
                            context.startActivity(intent)
                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            Color.White
                        )
                    ) {
                        AsyncImage(
                            model = IMAGE_BASE_URL + PIC_POSTER_PATH + people.poster,
                            contentDescription = "people",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = people.name.uppercase(),
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