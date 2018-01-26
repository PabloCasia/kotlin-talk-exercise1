package com.pablocasia.kotlinmovies

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pablocasia.kotlinmovies.adapter.ReviewsAdapter
import com.pablocasia.kotlinmovies.adapter.TrailersAdapter
import com.pablocasia.kotlinmovies.domain.Movie
import com.pablocasia.kotlinmovies.remote.MovieRemote
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


class MovieDetailsActivity : AppCompatActivity() {

    private val movie: Movie
            by lazy { intent.extras.getParcelable<Movie>(MOVIE_KEY) }

    private lateinit var trailersAdapter: TrailersAdapter
    private lateinit var reviewsAdapter: ReviewsAdapter

    private val movieRemote: MovieRemote = MovieRemote()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initializeAdapters()
        initializeRecyclerViews()

        requestReviews()
        requestTrailers()

        detail_toolbar.title = movie.title

        movie_detail_rating.text = movie.vote_average.toString()
        movie_detail_plot.text = movie.overview
        movie_detail_release_date.text = movie.release_date.getSpainFormatDate()

        movie_detail_add_to_favorites.setOnClickListener {
            toast("Favorita")
        }

        Picasso.with(this).load(movie.backdropUrl).noPlaceholder().into(backdrop)
    }

    private fun initializeRecyclerViews(){
        movie_detail_reviews_recycler_view.adapter = reviewsAdapter
        movie_detail_trailers_recycler_view.adapter = trailersAdapter
    }

    private fun initializeAdapters(){
        trailersAdapter = TrailersAdapter({
            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
        })

        reviewsAdapter = ReviewsAdapter({
            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
        })
    }

    private fun requestTrailers() {
        doAsync {
            trailersAdapter.trailers = movieRemote.getTrailers(movie.id).toMutableList()
            uiThread {
                trailersAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun requestReviews() {
        doAsync {
            reviewsAdapter.reviews = movieRemote.getReviews(movie.id).toMutableList()
            uiThread {
                reviewsAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val MOVIE_KEY = "MOVIE"

        fun getCallingIntent(context: Context,
                             movie: Movie): Intent {
            val callingIntent = Intent(context, MovieDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_KEY, movie)
            callingIntent.putExtras(bundle)
            return callingIntent
        }
    }
}