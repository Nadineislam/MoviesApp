package com.example.movieapp.movie_home_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("genres")
    val categoriesList: List<Categories>
)
