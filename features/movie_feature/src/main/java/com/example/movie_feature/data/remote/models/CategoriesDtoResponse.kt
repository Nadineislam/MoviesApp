package com.example.movie_feature.data.remote.models

import com.google.gson.annotations.SerializedName

data class CategoriesDtoResponse(
    @SerializedName("genres")
    val categoriesList: List<CategoriesDto>
)
