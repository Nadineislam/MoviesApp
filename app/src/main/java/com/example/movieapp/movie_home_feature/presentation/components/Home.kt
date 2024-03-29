package com.example.movieapp.movie_home_feature.presentation.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_NAME
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_OVERVIEW
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_POSTER
import com.example.movieapp.core.utils.Constants.Companion.MOVIE_VOTE
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
    TrendingList(movies) { item ->
        val intent = Intent(context, MovieDetails::class.java)
        intent.putExtra(MOVIE_OVERVIEW, (item as? Movies)?.overView ?: "")
        intent.putExtra(MOVIE_NAME, (item as? Movies)?.name ?: "")
        intent.putExtra(MOVIE_POSTER, (item as? Movies)?.posterPath ?: "")
        intent.putExtra(MOVIE_VOTE, (item as? Movies)?.voteAverage ?: 0.7)
        context.startActivity(intent)
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
    TrendingList(tv) { item ->
        val intent = Intent(context, MovieDetails::class.java)
        intent.putExtra(MOVIE_OVERVIEW, (item as? Tv)?.overView ?: "")
        intent.putExtra(MOVIE_NAME, (item as? Tv)?.name ?: "")
        intent.putExtra(MOVIE_POSTER, (item as? Tv)?.poster ?: "")
        intent.putExtra(MOVIE_VOTE, (item as? Tv)?.voteAverage ?: 7.0)
        context.startActivity(intent)
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
    TrendingList(people) { item ->
        val intent = Intent(context, MovieDetails::class.java)
        intent.putExtra(MOVIE_NAME, (item as? People)?.name ?: "")
        intent.putExtra(MOVIE_POSTER, (item as? People)?.poster ?: "")
        context.startActivity(intent)
    }
}