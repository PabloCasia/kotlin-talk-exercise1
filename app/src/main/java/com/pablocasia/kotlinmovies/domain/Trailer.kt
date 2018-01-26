package com.pablocasia.kotlinmovies.domain

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 21/01/2018.
 */
data class Trailer(val id: String,
                   val key: String,
                   val name: String,
                   val site: String,
                   val type: String) {
    val url: String
        get() = "http://www.youtube.com/watch?v=$key"

    val image:String
        get() = "http://img.youtube.com/vi/$key/0.jpg"
}