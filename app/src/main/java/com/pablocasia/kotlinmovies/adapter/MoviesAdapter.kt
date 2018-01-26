package com.pablocasia.kotlinmovies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pablocasia.kotlinmovies.domain.Movie
import com.pablocasia.kotlinmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
class MoviesAdapter(private val listener: (Movie) -> Unit)
    : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount() = movies.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Movie, listener: (Movie) -> Unit) {
            itemView.apply {
                Picasso.with(context).load(item.coverUrl).noPlaceholder().into(movieCover)
                setOnClickListener { listener(item) }
            }
        }
    }
}