package com.example.movie_feature.domain.models

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)