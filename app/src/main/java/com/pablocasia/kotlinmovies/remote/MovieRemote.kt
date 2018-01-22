package com.pablocasia.kotlinmovies.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
class MovieRemote {

    private val baseUrl = "http://api.themoviedb.org/3/"
    private val apiKey = "api-key" //TODO #1: Change API key
    private val movieService: MovieService
            by lazy { makeMovieService(makeOkHttpClient(), makeGson()) }

    fun getMovies(orderCriteria: OrderCriteria): List<Movie> {
        val call = movieService.getMovies(orderCriteria.apiRequest, apiKey)
        val result = call.execute().body()

        return result?.results ?: listOf()
    }

    fun getTrailers(movieId:String): List<Trailer> {
        val call = movieService.getTrailers(movieId, apiKey)
        val result = call.execute().body()

        return result?.results ?: listOf()
    }

    fun getReviews(movieId:String): List<Review> {
        val call = movieService.getReviews(movieId, apiKey)
        val result = call.execute().body()

        return result?.results ?: listOf()
    }

    private fun makeGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        return gsonBuilder.create()
    }

    private fun makeOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client.build()
    }

    private fun makeMovieService(okHttpClient: OkHttpClient, gson: Gson): MovieService {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
        return retrofit.create(MovieService::class.java)
    }
}