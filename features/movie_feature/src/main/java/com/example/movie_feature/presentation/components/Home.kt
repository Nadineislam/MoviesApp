package com.example.movie_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movie_feature.R
import com.example.movie_feature.presentation.intents.HomeIntent
import com.example.movie_feature.presentation.viewmodel.HomeViewModel
import com.example.movie_feature.presentation.viewstates.HomeViewState
import com.example.presentation.StateRenderer

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val homeState by viewModel.stateRendererFlow.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.processIntent(HomeIntent.LoadAllTrendingData)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            HomeLayout(navController)
            TitleText(text = "Trending Movies")
            GetTrendingMovies(state = homeState, navController = navController)
            TitleText(text = "Trending TV")
            GetTrendingTv(state = homeState, navController = navController)
            TitleText(text = "Trending People")
            GetTrendingPeople(state = homeState, navController = navController)
        }
    }
}

@Composable
fun HomeLayout(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
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
            contentDescription = "search",
            modifier = Modifier
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
fun GetTrendingMovies(state: StateRenderer<HomeViewState, Any>, navController: NavController) {
    GetHomeResourceList(
        state = state,
        onSuccessMovies = { movies ->
            TrendingList(
                items = movies?.trendingMovies ?: emptyList(),
                navController = navController
            )
        }
    )
}

@Composable
fun GetTrendingTv(state: StateRenderer<HomeViewState, Any>, navController: NavController) {
    GetHomeResourceList(
        state = state,
        onSuccessTvShows = { tvShows ->
            TrendingList(
                items = tvShows?.trendingTv ?: emptyList(),
                navController = navController
            )
        }
    )
}

@Composable
fun GetTrendingPeople(state: StateRenderer<HomeViewState, Any>, navController: NavController) {
    GetHomeResourceList(
        state = state,
        onSuccessPeople = { people ->
            TrendingList(
                items = people?.trendingPeople ?: emptyList(),
                navController = navController
            )
        }
    )
}