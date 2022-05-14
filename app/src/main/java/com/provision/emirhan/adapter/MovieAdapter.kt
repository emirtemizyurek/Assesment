package com.provision.emirhan.adapter

import com.provision.emirhan.R
import com.provision.emirhan.network.model.MovieDetails
import com.provision.emirhan.view.DetailsActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MovieAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var list: List<MovieDetails> = ArrayList()


    fun setMovieList(list: List<MovieDetails>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = list[position].title
        Picasso.get().load(list[position].poster).into(holder.imageViewMoviePoster)
        holder.movieReleaseDate.text = list[position].year
        holder.childView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("title", list[position].title)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_single_movie,
                parent,
                false
            )
        )
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageViewMoviePoster: ImageView = v.findViewById(R.id.imageView_movie_poster)
        val movieTitle: TextView = v.findViewById(R.id.movie_title)
        val movieReleaseDate: TextView = v.findViewById(R.id.movie_release_date)
        var childView: LinearLayout = v.findViewById(R.id.child)
    }

}