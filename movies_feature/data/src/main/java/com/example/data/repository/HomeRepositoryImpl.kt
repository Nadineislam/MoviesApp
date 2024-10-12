package com.example.data.repository

import com.example.data.remote.MoviesApi
import com.example.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val moviesApi: MoviesApi) :
    HomeRepository {
    override suspend fun getTrendingMovies() = moviesApi.getTrendingMovies()

    override suspend fun getTrendingTv() = moviesApi.getTrendingTv()

    override suspend fun getTrendingPeople() = moviesApi.getTrendingPeople()

    override suspend fun getSearchMovie(movieName: String) = moviesApi.getSearchedMovie(movieName)


}