package com.pablocasia.kotlinmovies

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 21/01/2018.
 */

fun String.getSpainFormatDate(): String {
    val parts = this.split("-")
    return if (parts.size == 3) "${parts[2]}-${parts[1]}-${parts[0]}" else this
}