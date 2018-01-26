package com.pablocasia.kotlinmovies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pablocasia.kotlinmovies.R
import com.pablocasia.kotlinmovies.domain.Review
import kotlinx.android.synthetic.main.review_item.view.*

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
class ReviewsAdapter(private val listener: (Review) -> Unit)
    : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    var reviews: MutableList<Review> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.review_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviews[position], listener)
    }

    override fun getItemCount() = reviews.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Review, listener: (Review) -> Unit) {
            itemView.apply {
                author.text = item.author
                review.text =
                        if (item.content.length < 300)
                            item.content
                        else
                            "${item.content.subSequence(0, 300)}..."
                setOnClickListener { listener(item) }
            }
        }
    }
}