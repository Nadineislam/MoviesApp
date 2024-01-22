package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.core.utils.handleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.SearchMovieUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingMoviesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingPeopleUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val trendingMoviesUseCase: TrendingMoviesUseCase,
    private val trendingTvUseCase: TrendingTvUseCase,
    private val trendingPeopleUseCase: TrendingPeopleUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
    private val _movies: MutableStateFlow<Resource<TrendingMoviesResponse>> =
        MutableStateFlow(Resource.Loading())
    val movies = _movies.asStateFlow()
    private val _tv: MutableStateFlow<Resource<TrendingTvResponse>> =
        MutableStateFlow(Resource.Loading())
    val tv = _tv.asStateFlow()
    private val _people: MutableStateFlow<Resource<TrendingPeopleResponse>> =
        MutableStateFlow(Resource.Loading())
    val people = _people.asStateFlow()

    private val _searchMovie = MutableStateFlow<Resource<TrendingTvResponse>>(Resource.Loading())
    val searchMovie = _searchMovie.asStateFlow()

    init {
        getTrendingMovies()
        getTrendingTv()
        getTrendingPeople()
    }


    fun getTrendingMovies() = viewModelScope.launch {
        val response = trendingMoviesUseCase()
        _movies.value = handleResponse(response)
    }

    fun getTrendingTv() = viewModelScope.launch {
        val response = trendingTvUseCase()
        _tv.value = handleResponse(response)
    }

    fun getTrendingPeople() = viewModelScope.launch {
        val response = trendingPeopleUseCase()
        _people.value = handleResponse(response)
    }

    fun getSearchedMovie(movie: String) {
        viewModelScope.launch {
            val response = searchMovieUseCase(movie)
            _searchMovie.value = handleResponse(response)
        }
    }
}



