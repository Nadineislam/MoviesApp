package com.example.movieapp.movie_home_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val poster: String
)
