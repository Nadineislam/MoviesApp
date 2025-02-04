package com.example.movie_feature.domain.models

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("genres")
    val categoriesList: List<Categories>
)
