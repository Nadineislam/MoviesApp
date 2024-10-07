package com.example.movieapp.movie_home_feature.presentation.components

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.movie_home_feature.presentation.intents.HomeIntent
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel
import com.example.movieapp.movie_home_feature.presentation.viewstates.HomeViewState


@Composable
fun HomeScreen(viewModel: HomeViewModel= hiltViewModel(), navController: NavController) {
    val homeState by viewModel.homeState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.processIntent(HomeIntent.LoadAllTrendingData)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            HomeLayout(navController)
            TitleText(text = "Trending Movies")
            GetTrendingMovies(state = homeState,navController = navController)
            TitleText(text = "Trending TV")
            GetTrendingTv(state = homeState, navController = navController)
            TitleText(text = "Trending People")
            GetTrendingPeople(state = homeState, navController = navController)
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
fun GetTrendingMovies(state: HomeViewState,navController: NavController) {
    GetHomeResourceList(
        state = state,
        emptyListMessage = "Error fetching movies",
        onSuccessMovies = { movies ->
            TrendingList(items = movies?.trendingMovies?: emptyList(), navController = navController)
        }
    )
}

@Composable
fun GetTrendingTv(state: HomeViewState,navController: NavController){
    GetHomeResourceList(
        state = state,
        emptyListMessage = "Error fetching TV shows",
        onSuccessTvShows = { tvShows ->
            TrendingList(items = tvShows?.trendingTv?: emptyList(), navController = navController)
        }
    )
}

@Composable
fun GetTrendingPeople(state: HomeViewState,navController: NavController) {
    GetHomeResourceList(
        state = state,
        emptyListMessage = "Error fetching people",
        onSuccessPeople = { people ->
            TrendingList(items = people?.trendingPeople?: emptyList(), navController = navController)
        }
    )
}