package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvCategoriesUseCase: TvCategoriesUseCase,
    private val tvCategoryUseCase: TvCategoryUseCase
) : ViewModel() {
    private val _categories: MutableStateFlow<Resource<CategoriesResponse>> =
        MutableStateFlow(Resource.Loading())
    val categories: StateFlow<Resource<CategoriesResponse>> = _categories

    private val _tvCategories: MutableStateFlow<Resource<TrendingTvResponse>> =
        MutableStateFlow(Resource.Loading())
    val tvCategory: StateFlow<Resource<TrendingTvResponse>> = _tvCategories

    private fun getCategories() = viewModelScope.launch {
        val response = tvCategoriesUseCase()
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

    fun getTvCategories(categoryId: Int) = viewModelScope.launch {
        val response = tvCategoryUseCase(categoryId)
        _tvCategories.value = handleMovieCategoriesResponse(response)
    }

    private fun handleMovieCategoriesResponse(response: Response<TrendingTvResponse>): Resource<TrendingTvResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error("An error occurred")
    }}