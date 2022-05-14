package com.provision.emirhan.view

import com.provision.emirhan.repository.DetailsActivityRepository
import com.provision.emirhan.viewModel.DetailsActivityViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.provision.emirhan.R
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailsActivityViewModel
    private lateinit var imageViewMoviePoster: AppCompatImageView

    private lateinit var movieTitle: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var moviePlot: TextView
    private lateinit var movieActors: TextView
    private lateinit var movieAwards: TextView
    private lateinit var movieDVD: TextView
    private lateinit var movieDirector: TextView
    private lateinit var movieGenre: TextView
    private lateinit var movieImdbRating: TextView
    private lateinit var movieLanguage: TextView
    private lateinit var movieRuntime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        val repository = DetailsActivityRepository(application)
        repository.getMovie(intent.getStringExtra("title").toString())


        initThis()

        //Burada Delay koyulmasının sebebi detaylar sayfası açıldığı zaman bu sayfa init
        // ediliyor ancak sunucu ile iletişimde geçilen süre göz ardı ediliyor ve tüm veriler null geliyordu. Delay bunu çözüyor.
        Handler().postDelayed({
            initDetails()
        }, 1000)
    }

    private fun initThis(){
        imageViewMoviePoster = findViewById(R.id.imageView_movie_poster)
        movieTitle = findViewById(R.id.movie_title)
        movieReleaseDate = findViewById(R.id.movie_release_date)
        moviePlot = findViewById(R.id.movie_plot)
        movieActors = findViewById(R.id.movie_actors)
        movieAwards = findViewById(R.id.movie_award)
        movieDVD = findViewById(R.id.movie_dvd)
        movieDirector = findViewById(R.id.movie_director)
        movieGenre = findViewById(R.id.movie_genre)
        movieImdbRating = findViewById(R.id.movie_imdb_rating)
        movieLanguage = findViewById(R.id.movie_language)
        movieRuntime = findViewById(R.id.movie_runtime)
    }

    private fun initDetails(){
        Log.e("Tagggg", DetailsActivityRepository.responseForDetails.value!!.actors)
        val actors = DetailsActivityRepository.responseForDetails.value!!.actors
        val awards = DetailsActivityRepository.responseForDetails.value!!.awards
        val dVD = DetailsActivityRepository.responseForDetails.value!!.dVD
        val director = DetailsActivityRepository.responseForDetails.value!!.director
        val genre = DetailsActivityRepository.responseForDetails.value!!.genre
        val imdbRating = DetailsActivityRepository.responseForDetails.value!!.imdbRating
        val language = DetailsActivityRepository.responseForDetails.value!!.language
        val plot = DetailsActivityRepository.responseForDetails.value!!.plot
        val runtime = DetailsActivityRepository.responseForDetails.value!!.runtime
        val released = DetailsActivityRepository.responseForDetails.value!!.released
        val poster = DetailsActivityRepository.responseForDetails.value!!.poster
        val title = DetailsActivityRepository.responseForDetails.value!!.title


        Picasso.get().load(poster).into(imageViewMoviePoster)
        movieTitle.text = title
        movieReleaseDate.text = released
        moviePlot.text = plot
        movieActors.text = actors
        movieAwards.text = awards
        movieDVD.text = dVD
        movieDirector.text = director
        movieGenre.text = genre
        movieImdbRating.text = imdbRating
        movieLanguage.text = language
        movieRuntime.text = runtime
    }
}