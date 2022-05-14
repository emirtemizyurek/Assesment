package com.provision.emirhan.network

import com.provision.emirhan.network.model.MovieDetails
import com.provision.emirhan.network.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="http://www.omdbapi.com/"
interface MoviesNetwork {

    @GET("?apikey=50689d4d")
    fun getMovies(@Query("s")searchString: String): Call<Search>

    @GET("?apikey=50689d4d")
    fun getMovieDetails(@Query("t")details: String): Call<MovieDetails>

}