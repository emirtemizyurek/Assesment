package com.provision.emirhan.view

import com.provision.emirhan.R
import com.provision.emirhan.adapter.MovieAdapter
import com.provision.emirhan.viewModel.SearchActivityViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class ListMovieActivity : AppCompatActivity() {
    lateinit var searchProgress: ProgressBar
    lateinit var editTextSearch: EditText
    lateinit var imageViewSearch: ImageView
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: SearchActivityViewModel
    private lateinit var recyclerViewSearch: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)

        initThis()

        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        initClickableItems()


        viewModel.showProgress.observe(this, Observer {
            if (it)
                searchProgress.visibility = View.VISIBLE
            else
                searchProgress.visibility = View.GONE
        })

        try {
            viewModel.moviesList.observe(this, Observer {
                adapter.setMovieList(it.search)
            })
            adapter = MovieAdapter(this)
            recyclerViewSearch.adapter = adapter
        } catch (e : Exception) {
            Toast.makeText(this,"The movie was not found. Please try again",Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

        checkIntentExtra()

    }

    private fun initThis(){
        editTextSearch = findViewById(R.id.et_search)
        searchProgress = findViewById(R.id.search_progress)
        recyclerViewSearch = findViewById(R.id.rv_search)
        imageViewSearch = findViewById(R.id.iv_search)
    }

    private fun initClickableItems(){
        imageViewSearch.setOnClickListener {
            if (editTextSearch.text!!.isNotEmpty())
                viewModel.searchLocation(editTextSearch.text.toString())
        }
    }

    private fun checkIntentExtra(){
        if (intent.hasExtra("Search")) {
            val image = intent.getStringExtra("Search")
            if (image != null) {
                editTextSearch.append(image)
                imageViewSearch.performClick()
            }
        }
    }
}