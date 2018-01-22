package com.pablocasia.kotlinmovies.remote

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
data class ResponsePage<out T>(val results: List<T>)