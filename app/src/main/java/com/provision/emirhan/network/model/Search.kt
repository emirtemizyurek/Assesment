package com.provision.emirhan.network.model


import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<MovieDetails>,
    @SerializedName("totalResults")
    val totalResults: String
)