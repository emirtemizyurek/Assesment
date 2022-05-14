package com.provision.emirhan.repository

import com.provision.emirhan.network.BASE_URL
import com.provision.emirhan.network.MoviesNetwork
import com.provision.emirhan.network.model.MovieDetails
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivityRepository(val application: Application) {
    val showProgress = MutableLiveData<Boolean>()
    val response = MutableLiveData<MovieDetails>()

    companion object{
        val responseForDetails = MutableLiveData<MovieDetails>()
    }

    fun getMovie(title: String) {
        Log.e("TAG", response.value.toString())
        showProgress.value = true

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()


        val service = retrofit.create(MoviesNetwork::class.java)
        service.getMovieDetails(title).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, resp: Response<MovieDetails>) {
                showProgress.value = false
                response.value = resp.body()
                responseForDetails.value = response.value
                Log.e("TAG2", response.value.toString())
                Log.e("Response2", responseForDetails.value.toString())

            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error while accessing the API", Toast.LENGTH_SHORT)
                    .show()
            }

        })


    }


}