package com.example.data.repository


import com.example.data.remote.MoviesApi
import com.example.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesApi: MoviesApi) :
    MoviesRepository {
    override suspend fun getMoviesCategoriesList() = moviesApi.getMoviesCategoriesList()

    override suspend fun getMovieCategoryList(page: Int, categoryId: Int) =
        moviesApi.getMovieCategoryList(page = page, categoryId = categoryId)

}