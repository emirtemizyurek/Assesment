package com.provision.emirhan.repository

import com.provision.emirhan.network.BASE_URL
import com.provision.emirhan.network.MoviesNetwork
import com.provision.emirhan.network.model.Search
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(val application: Application)  {

    val showProgress= MutableLiveData<Boolean>()
    val moviesList=MutableLiveData<Search>()
    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }
    fun searchLocation(searchString: String){
        showProgress.value=true

        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service=retrofit.create(MoviesNetwork::class.java)
        service.getMovies(searchString).enqueue(object: Callback<Search>{


            override fun onFailure(call: Call<Search>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application,"Error while accessing the API", Toast.LENGTH_SHORT).show()
                Log.e("ERROR",t.message.toString())
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                Log.d("SearchRepository" , "Response : ${Gson().toJson(response.body())}")
                moviesList.value = response.body()
                Log.e("SearchLog",moviesList.value.toString())
                showProgress.value = false
            }

        })
    }
}