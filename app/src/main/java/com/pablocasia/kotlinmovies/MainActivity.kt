package com.pablocasia.kotlinmovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.pablocasia.kotlinmovies.adapter.MoviesAdapter
import com.pablocasia.kotlinmovies.domain.Movie
import com.pablocasia.kotlinmovies.domain.OrderCriteria
import com.pablocasia.kotlinmovies.remote.MovieRemote
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MoviesAdapter

    private val movieRemote: MovieRemote = MovieRemote()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeAdapter()
        initializeRecyclerView()

        requestMovies(OrderCriteria.POPULAR)
    }

    private fun initializeRecyclerView() {
        recyclerViewMovies.setHasFixedSize(true)
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        recyclerViewMovies.adapter = adapter
    }

    private fun initializeAdapter() {
        adapter = MoviesAdapter({
            Log.i("TAG", "Click on movie: $it")
            launchMovieDetailsActivity(it)
        })
    }

    private fun launchMovieDetailsActivity(movie: Movie) {
        val intentToLaunch = MovieDetailsActivity.getCallingIntent(this, movie)
        this.startActivity(intentToLaunch)
    }

    private fun requestMovies(orderCriteria: OrderCriteria) {
        doAsync {
            adapter.movies = movieRemote.getMovies(orderCriteria).toMutableList()
            uiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionPopular -> {
                requestMovies(OrderCriteria.POPULAR)
                return true
            }
            R.id.actionTopRated -> {
                requestMovies(OrderCriteria.TOP_RATED)
                return true
            }
            R.id.actionLatest -> {
                requestMovies(OrderCriteria.LATEST)
                return true
            }
            R.id.actionIncoming -> {
                requestMovies(OrderCriteria.UPCOMING)
                return true
            }
            R.id.actionNowPlaying -> {
                requestMovies(OrderCriteria.NOW_PLAYING)
                return true
            }
            else {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
