package com.provision.emirhan.viewModel

import com.provision.emirhan.network.model.Search
import com.provision.emirhan.repository.SearchActivityRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class SearchActivityViewModel(application: Application): AndroidViewModel(application) {
private val repository= SearchActivityRepository(application)
    val showProgress: LiveData<Boolean>
    val moviesList: LiveData<Search>

    init {
        this.showProgress=repository.showProgress
        this.moviesList=repository.moviesList
    }

    fun searchLocation(searchString: String){
        repository.searchLocation(searchString)
    }
}