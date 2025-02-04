package com.example.movie_feature.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movie_feature.presentation.intents.HomeIntent
import com.example.movie_feature.presentation.viewmodel.HomeViewModel

@Composable
fun SearchScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val searchQuery = remember { mutableStateOf("") }
    val homeState by viewModel.stateRendererFlow.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery.value,
            onValueChange = { query ->
                searchQuery.value = query
                viewModel.processIntent(HomeIntent.SearchMovies(query))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = "Search Movies..") }
        )

        GetHomeResourceList(
            state = homeState,
            onSuccessSearchResults = { searchResults ->
                val tvList = searchResults?.trendingTv
                tvList?.let { tvs ->
                    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
                        items(tvs) { tv ->
                            TvItem(tv = tv, navController = navController)
                        }
                    }
                }
            }
        )
    }
}