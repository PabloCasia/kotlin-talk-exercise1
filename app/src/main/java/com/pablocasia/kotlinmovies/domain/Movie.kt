package com.pablocasia.kotlinmovies.domain

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Pablo Garcia Fernandez (PabloCasia) on 20/01/2018.
 */
@Parcelize
data class Movie(val id: String,
                 val title: String,
                 private val poster_path: String,
                 val vote_average: Double,
                 val release_date: String,
                 val overview: String,
                 private val backdrop_path: String) : Parcelable {

    val coverUrl: String
        get() {
            return "http://image.tmdb.org/t/p/w185/$poster_path"
        }

    val backdropUrl: String
        get() = "http://image.tmdb.org/t/p/w500/$backdrop_path"

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel.readString(),
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readDouble(),
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readString())
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}