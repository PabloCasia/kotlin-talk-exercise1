package com.pablocasia.kotlinmovies.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
interface MovieService {

    @GET("movie/{order_criteria}")
    fun getMovies(@Path("order_criteria") orderCriteria: String,
                  @Query("api_key") apiKey: String)
            : Call<ResponsePage<Movie>>

    @GET("movie/{movie_id}/videos")
    fun getTrailers(@Path("movie_id") movieId: String,
                  @Query("api_key") apiKey: String)
            : Call<ResponsePage<Trailer>>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(@Path("movie_id") movieId: String,
                    @Query("api_key") apiKey: String)
            : Call<ResponsePage<Review>>
}