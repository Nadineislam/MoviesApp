package com.example.movieapp.movie_home_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.use_case.SearchMovieUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingMoviesUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingPeopleUseCase
import com.example.movieapp.movie_home_feature.domain.use_case.TrendingTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
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
    val movies: StateFlow<Resource<TrendingMoviesResponse>> = _movies
    private val _tv: MutableStateFlow<Resource<TrendingTvResponse>> =
        MutableStateFlow(Resource.Loading())
    val tv: StateFlow<Resource<TrendingTvResponse>> = _tv
    private val _people: MutableStateFlow<Resource<TrendingPeopleResponse>> =
        MutableStateFlow(Resource.Loading())
    val people: StateFlow<Resource<TrendingPeopleResponse>> = _people

    private val _searchMovie = MutableStateFlow<Resource<TrendingTvResponse>>(Resource.Loading())
    val searchMovie: StateFlow<Resource<TrendingTvResponse>> = _searchMovie

    init {
        getTrendingMovies()
        getTrendingTv()
        getTrendingPeople()
    }


    fun getTrendingMovies() = viewModelScope.launch {
        val response = trendingMoviesUseCase()
        _movies.value = handleMoviesResponse(response)
    }

    private fun handleMoviesResponse(response: Response<TrendingMoviesResponse>): Resource<TrendingMoviesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error("An error occurred")
    }

    fun getTrendingTv() = viewModelScope.launch {
        val response = trendingTvUseCase()
        _tv.value = handleTrendingTvResponse(response)
    }

    private fun handleTrendingTvResponse(response: Response<TrendingTvResponse>): Resource<TrendingTvResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("An error occurred")
    }

    fun getTrendingPeople() = viewModelScope.launch {
        val response = trendingPeopleUseCase()
        _people.value = handleTrendingPeopleResponse(response)
    }

    private fun handleTrendingPeopleResponse(response: Response<TrendingPeopleResponse>): Resource<TrendingPeopleResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("An error occurred")
    }

    fun getSearchedMovie(movie: String) {
        viewModelScope.launch {
            val response = searchMovieUseCase(movie)
            _searchMovie.value = handleSearchMovieResponse(response)
        }
    }

    private fun handleSearchMovieResponse(response: Response<TrendingTvResponse>): Resource<TrendingTvResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("An error occurred")
    }
}



