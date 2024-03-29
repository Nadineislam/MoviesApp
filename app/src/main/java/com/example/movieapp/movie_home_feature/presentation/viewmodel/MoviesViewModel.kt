package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.core.utils.handleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.domain.use_case.MoviesCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.MovieCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val categories = _categories.asStateFlow()

    private val _movieCategories: MutableStateFlow<Resource<TrendingMoviesResponse>> =
        MutableStateFlow(Resource.Loading())
    val movieCategories = _movieCategories.asStateFlow()

    private var currentPage = 1
    private var isLoading = false
    var currentCategory: Int? = null

    init {
        getCategories()
    }

    fun getCategories() = viewModelScope.launch {
        val response = moviesCategoriesUseCase()
        _categories.emit(handleResponse(response))
    }

    fun getMovieCategories(categoryId: Int) = viewModelScope.launch {
        if (isLoading) return@launch

        isLoading = true

        val response = movieCategoryUseCase(currentPage, categoryId)
        _movieCategories.emit(handlePaginatedResponse(response))
        currentPage += 1
        isLoading = false

        currentCategory = categoryId
    }

    private fun handlePaginatedResponse(response: Response<TrendingMoviesResponse>): Resource<TrendingMoviesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val currentMovie =
                    _movieCategories.value.data?.trendingMovies.orEmpty().toMutableList()
                currentMovie.addAll(resultResponse.trendingMovies)
                return Resource.Success(TrendingMoviesResponse(resultResponse.page, currentMovie))
            }
        }
        return Resource.Error("An error occurred")
    }

}