package com.example.movie_feature.data.remote.models

import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)