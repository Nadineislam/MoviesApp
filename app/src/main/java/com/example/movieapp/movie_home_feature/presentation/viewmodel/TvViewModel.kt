package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.core.utils.handleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoriesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TvCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvCategoriesUseCase: TvCategoriesUseCase,
    private val tvCategoryUseCase: TvCategoryUseCase
) : ViewModel() {
    private val _categories: MutableStateFlow<Resource<CategoriesResponse>> =
        MutableStateFlow(Resource.Loading())
    val categories = _categories.asStateFlow()

    private val _tvCategories: MutableStateFlow<Resource<TrendingTvResponse>> =
        MutableStateFlow(Resource.Loading())
    val tvCategory = _tvCategories.asStateFlow()

    private var currentPage = 1
    private var isLoading = false
    var currentCategory: Int? = null

    fun getCategories() = viewModelScope.launch {
        val response = tvCategoriesUseCase()
        _categories.emit(handleResponse(response))
    }

    init {
        getCategories()
    }

    fun getTvCategories(categoryId: Int) = viewModelScope.launch {

        if (isLoading) return@launch

        isLoading = true
        val response = tvCategoryUseCase(currentPage, categoryId)
        _tvCategories.emit(handleResponse(response))
        delay(5000)
        currentPage += 1
        isLoading = false

        currentCategory = categoryId
    }
}