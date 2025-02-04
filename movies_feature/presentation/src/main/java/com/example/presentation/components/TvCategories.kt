//package com.example.presentation.components
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.navigation.NavController
//import com.example.presentation.viewmodel.TvViewModel
//
//@Composable
//fun TvCategoriesScreen(navController: NavController, viewModel: TvViewModel = hiltViewModel()) {
//    val categoriesState by viewModel.state.collectAsStateWithLifecycle()
//
//    GetTvResourceList(
//        state = categoriesState,
//        emptyListMessage = "Error fetching categories",
//        onSuccessCategories = { categories ->
//            Categories(
//                categories = categories?.categoriesList ?: emptyList(),
//                navigateToCategory = { categoryId ->
//                    navController.navigate("tv_category_screen/$categoryId")
//
//                }
//            )
//        }
//    )
//}