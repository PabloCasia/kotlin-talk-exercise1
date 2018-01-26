package com.pablocasia.kotlinmovies.domain

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
enum class OrderCriteria(val apiRequest: String) {
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    NOW_PLAYING("now_playing"),
    LATEST("latest"),
    UPCOMING("upcoming")
}