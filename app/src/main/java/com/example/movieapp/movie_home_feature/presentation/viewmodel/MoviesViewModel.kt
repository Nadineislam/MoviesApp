package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.domain.use_case.MoviesCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.MovieCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesCategoriesUseCase: MoviesCategoriesUseCase,
    private val movieCategoryUseCase: MovieCategoryUseCase
) : ViewModel() {
    private val _categories: MutableStateFlow<Resource<CategoriesResponse>> =
        MutableStateFlow(Resource.Loading())
    val categories: StateFlow<Resource<CategoriesResponse>> = _categories

    private val _movieCategories: MutableStateFlow<Resource<TrendingMoviesResponse>> =
        MutableStateFlow(Resource.Loading())
    val movieCategories: StateFlow<Resource<TrendingMoviesResponse>> = _movieCategories

    fun getCategories() = viewModelScope.launch {
        val response = moviesCategoriesUseCase()
        _categories.value = handleCategoriesResponse(response)
    }

    init {
        getCategories()
    }

    private fun handleCategoriesResponse(response: Response<CategoriesResponse>): Resource<CategoriesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error("An error occurred")
    }

    fun getMovieCategories(categoryId: Int) = viewModelScope.launch {
        val response = movieCategoryUseCase(categoryId)
        _movieCategories.value = handleMovieCategoriesResponse(response)
    }

    private fun handleMovieCategoriesResponse(response: Response<TrendingMoviesResponse>): Resource<TrendingMoviesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error("An error occurred")
    }
}