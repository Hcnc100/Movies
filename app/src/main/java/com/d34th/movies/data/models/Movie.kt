package com.d34th.movies.data.models

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    @SerializedName("adult")
    val isForAdults: Boolean = false,
    @SerializedName("backdrop_path")
    val urlImageBackground: String = "",
    val id: Int = -1,
    @SerializedName("original_language")
    val language: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    @SerializedName("poster_path")
    val urlImagePoster: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1,
    val movieType: String,

    ) : Parcelable {
    override fun toString(): String {
        return "\ntitle:$title"
    }

    companion object {
        fun toMovieEntity(
            movie: Movie,
            movieType: String,
        ): MovieEntity {
            with(movie) {
                return MovieEntity(
                    isForAdults,
                    id,
                    language,
                    originalTitle,
                    overview,
                    popularity,
                    release_date,
                    title,
                    video,
                    vote_average,
                    vote_count,
                    movieType,
                    urlImagePoster,
                    urlImageBackground,
                )
            }
        }
    }
}


data class MovieList(
    val results: List<Movie> = emptyList()
)


//Room

@Parcelize
@Entity
data class MovieEntity(
    val isForAdults: Boolean = false,
    @PrimaryKey
    val id: Int = -1,
    val language: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1,
    val movieType: String,
    val urlImagePoster: String,
    val urlImageBackground: String,
    var bitmapPoster: Bitmap? = null,
    var bitmapBackground: Bitmap? = null,
) : Parcelable


