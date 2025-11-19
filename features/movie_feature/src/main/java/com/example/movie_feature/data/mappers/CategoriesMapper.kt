package com.example.movie_feature.data.mappers

import com.example.movie_feature.data.remote.models.CategoriesDto
import com.example.movie_feature.data.remote.models.CategoriesDtoResponse
import com.example.movie_feature.domain.models.Categories
import com.example.movie_feature.domain.models.CategoriesResponse

fun CategoriesDtoResponse.toCategoriesModel(): CategoriesResponse {
    return CategoriesResponse(categoriesList = this.categoriesList.map { it.toCategories() })
}

fun CategoriesDto.toCategories(): Categories {
    return Categories(id = this.id, name = this.name)
}