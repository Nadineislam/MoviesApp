package com.example.movie_feature.data.repository

import com.example.data.mapper.toDomain
import com.example.data.source.NetworkDataSource
import com.example.domain.result.OutCome
import com.example.movie_feature.data.remote.MoviesService
import com.example.movie_feature.domain.repository.TvRepository
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource<MoviesService>
) : TvRepository {
    override suspend fun getTvCategoriesList() = networkDataSource.performRequest(
        request = { getTvCategoriesList() },
        onSuccess = { response, _ -> OutCome.success(response) },
        onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
    )

    override suspend fun getTvCategoryList(page: Int, categoryId: Int) =
        networkDataSource.performRequest(
            request = { getTvCategoryList(page = page, categoryId = categoryId) },
            onSuccess = { response, _ -> OutCome.success(response) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )
}