package com.example.movie_feature.data.remote.models

import com.google.gson.annotations.SerializedName

data class PeopleDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val poster: String?
)
