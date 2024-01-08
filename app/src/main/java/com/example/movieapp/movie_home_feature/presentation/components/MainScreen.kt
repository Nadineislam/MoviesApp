package com.example.movieapp.movie_home_feature.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.movie_home_feature.presentation.viewmodel.HomeViewModel
import com.example.movieapp.movie_home_feature.presentation.viewmodel.MoviesViewModel
import com.example.movieapp.movie_home_feature.presentation.viewmodel.TvViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: HomeViewModel,
    moviesViewModel: MoviesViewModel,
    tvViewModel: TvViewModel
) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController = navController) }) { paddingValues ->
        BottomNavGraph(
            viewModel = viewModel,
            navController = navController,
            moviesViewModel,
            tvViewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens =
        listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Movies,
            BottomBarScreen.Tv
        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen, currentDestination: NavDestination?,
    navController: NavHostController
) {
    val imageVectorId = screen.icon
    NavigationBarItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(painterResource(id = imageVectorId), contentDescription = "Navigation icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }

    )

}