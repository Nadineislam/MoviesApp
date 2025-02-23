package com.example.movie_feature.domain.models

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val poster: String
)
