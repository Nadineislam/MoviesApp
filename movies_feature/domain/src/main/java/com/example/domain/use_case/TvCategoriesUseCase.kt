package com.example.domain.use_case

import com.example.domain.models.CategoriesResponse
import com.example.domain.repository.TvRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class TvCategoriesUseCase @Inject constructor(private val repository: TvRepository) {
    suspend operator fun invoke(): Response<CategoriesResponse> =
        repository.getTvCategoriesList()
}