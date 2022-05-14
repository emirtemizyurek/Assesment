package com.provision.emirhan.viewModel

import com.provision.emirhan.network.model.MovieDetails
import com.provision.emirhan.repository.DetailsActivityRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DetailsActivityRepository(application)
    private val showProgress: LiveData<Boolean>
    private val response: MutableLiveData<MovieDetails>


    init {
        showProgress = repository.showProgress
        response = repository.response
    }
}