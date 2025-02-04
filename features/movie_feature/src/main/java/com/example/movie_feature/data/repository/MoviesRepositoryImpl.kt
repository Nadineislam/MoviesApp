package com.example.movie_feature.data.repository


import com.example.data.mapper.toDomain
import com.example.data.source.NetworkDataSource
import com.example.domain.result.OutCome
import com.example.movie_feature.data.remote.MoviesService
import com.example.movie_feature.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource<MoviesService>) :
    MoviesRepository {
    override suspend fun getMoviesCategoriesList() = networkDataSource.performRequest(
        request = { getMoviesCategoriesList() },
        onSuccess = { response, _ -> OutCome.success(response) },
        onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
    )

    override suspend fun getMovieCategoryList(page: Int, categoryId: Int) =
        networkDataSource.performRequest(
            request = { getMovieCategoryList(page = page, categoryId = categoryId) },
            onSuccess = { response, _ -> OutCome.success(response) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )

}