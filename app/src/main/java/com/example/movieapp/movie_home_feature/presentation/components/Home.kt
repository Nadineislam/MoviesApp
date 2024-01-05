package com.example.movieapp.movie_home_feature.presentation.components

import android.widget.Toast
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import coil.compose.rememberImagePainter
import com.example.movieapp.R
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.MoviesApi.Companion.IMAGE_BASE_URL
import com.example.movieapp.movie_home_feature.data.remote.MoviesApi.Companion.PIC_POSTER_PATH
import com.example.movieapp.movie_home_feature.data.remote.dto.Movies
import com.example.movieapp.movie_home_feature.data.remote.dto.People
import com.example.movieapp.movie_home_feature.data.remote.dto.Tv
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            HomeLayout(navController)
            TitleText(text = "Trending Movies")
            GetPopularMeals(viewModel = viewModel)
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
fun GetPopularMeals(viewModel: HomeViewModel) {
    val trendingMoviesState by viewModel.movies.collectAsStateWithLifecycle()
    when (val resource = trendingMoviesState) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val movies = resource.data?.trendingMovies
            PopularMealList(movieThumbs = movies ?: emptyList())
        }

        is Resource.Error -> {
            val message = resource.message ?: "Error fetching movies"
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
                .show()
        }
    }
}

@Composable
fun PopularMealList(movieThumbs: List<Movies>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //   .height(260.dp)
            .fillMaxHeight()
    ) {
        LazyRow {
            items(movieThumbs) { movie ->
                Card(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight()
                        .width(140.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            Color.White
                        )
                    ) {
                        val painter = rememberImagePainter(
                            data = IMAGE_BASE_URL + PIC_POSTER_PATH + movie.posterPath,
                            builder = {
                                placeholder(R.drawable.ic_search)
                                error(R.drawable.ic_search)
                            }
                        )
                        Image(
                            painter = painter,
                            contentDescription = "food",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
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
    when (val resource = trendingTvState) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val people = resource.data?.trendingTv
            TrendingTvList(tvThumbs = people ?: emptyList())
        }

        is Resource.Error -> {
            val message = resource.message ?: "Error fetching movies"
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
                .show()
        }
    }
}

@Composable
fun TrendingTvList(tvThumbs: List<Tv>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow {
            items(tvThumbs) { tv ->
                Card(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight()
                        .width(140.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            Color.White
                        )
                    ) {
                        val painter = rememberImagePainter(
                            data = IMAGE_BASE_URL + PIC_POSTER_PATH + tv.poster,
                            builder = {
                                placeholder(R.drawable.ic_search)
                                error(R.drawable.ic_search)
                            }
                        )
                        Image(
                            painter = painter,
                            contentDescription = "food",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
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
    when (val resource = trendingPeopleState) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val people = resource.data?.trendingPeople
            TrendingPeopleList(tvThumbs = people ?: emptyList())
        }

        is Resource.Error -> {
            val message = resource.message ?: "Error fetching news"
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
                .show()
        }
    }
}

@Composable
fun TrendingPeopleList(tvThumbs: List<People>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //   .height(260.dp)
            .fillMaxHeight()
    ) {
        LazyRow {
            items(tvThumbs) { people ->
                Card(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight()
                        .width(140.dp)
//                        .clickable {
//                            val intent = Intent(context, MealDetails::class.java)
//                            intent.putExtra("MEAL_ID", meal.idMeal)
//                            intent.putExtra("MEAL_NAME", meal.strMeal)
//                            intent.putExtra("MEAL_THUMB", meal.strMealThumb)
//                            context.startActivity(intent)
//                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            Color.White
                        )
                    ) {
                        val painter = rememberImagePainter(
                            data = IMAGE_BASE_URL + PIC_POSTER_PATH + people.poster,
                            builder = {
                                placeholder(R.drawable.ic_search)
                                error(R.drawable.ic_search)
                            }
                        )
                        Image(
                            painter = painter,
                            contentDescription = "food",
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