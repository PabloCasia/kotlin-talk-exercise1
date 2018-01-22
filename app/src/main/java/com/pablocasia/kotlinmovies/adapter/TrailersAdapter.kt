package com.pablocasia.kotlinmovies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pablocasia.kotlinmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trailer_item.view.*


/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
class TrailersAdapter(private val listener: (Trailer) -> Unit)
    : RecyclerView.Adapter<TrailersAdapter.ViewHolder>() {

    var trailers: MutableList<Trailer> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.trailer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trailers[position], listener)
    }

    override fun getItemCount() = trailers.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Trailer, listener: (Trailer) -> Unit) {
            itemView.apply {
                Picasso.with(context).load(item.image).noPlaceholder().into(thumbnailVideo)

                setOnClickListener { listener(item) }
            }
        }
    }
}