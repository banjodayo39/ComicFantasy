package com.example.eBox.data.remote

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieDataResponse(
    var id: Int = 0,
    var page: Int = 0,
    @SerializedName("results")
    var results: List<MovieData?>? = null,
    var title: String = "",
    var total_pages: Int? = null,
    var total_results: Int? = null
    //var trailer: Trailer?=null
) : Parcelable

@Parcelize
open class MovieData(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("genre_ids")
    var genre_ids: List<Int?>? = null,
    @SerializedName("original_language")
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    //var popularity: Double? = null,
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("release_date")
    var release_date: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("vote_average")
    var vote_average: Double? = null,
    var vote_count: Int? = null
) : Parcelable

@Parcelize
@Entity(tableName = "popular")
data class PopularMovie(
    @PrimaryKey
    @NonNull
    var movieId: Int = 0,
    var popularity: Double? = null

) : MovieData(), Parcelable

@Parcelize
@Entity(tableName = "topRated")
data class TopRatedMovie(
    @PrimaryKey
    @NonNull
    var movieId: Int = 0,
    var popularity: Double? = null

) : MovieData(), Parcelable

@Parcelize
@Entity(tableName = "upcoming")
data class UpComing(
    @PrimaryKey
    @NonNull
    var movieId: Int = 0,
    var popularity: Double? = null

) : MovieData(), Parcelable


@Parcelize
@Entity(tableName = "now_showing")
data class NowShowing(
    @PrimaryKey
    @NonNull
    var movieId: Int = 0,
    var popularity: Double? = null
) : MovieData(), Parcelable

@Parcelize
@Entity(tableName = "latest")
data class Latest(
    @PrimaryKey
    @NonNull
    var movieId: Int = 0,
    var popularity: Double? = null,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("genre_ids")
    var genre_ids: List<Int?>? = null,
    @SerializedName("original_language")
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
//var popularity: Double? = null,
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("release_date")
    var release_date: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("vote_average")
    var vote_average: Double? = null,
    var vote_count: Int? = null
) :  Parcelable

data class Genre(
    var id: Int? = null,
    var name: String? = null
)

@Parcelize
data class Trailer(
    var movieId: Int = 0,
    var results: List<TrailerResult?>? = null
) : MovieData(), Parcelable

@Parcelize
data class TrailerResult(
    var id: String? = null,
    var iso_3166_1: String? = null,
    var iso_639_1: String? = null,
    var key: String? = null,
    var name: String? = null,
    var site: String? = null,
    var size: Int? = null,
    var type: String? = null
) : Parcelable

data class MovieRecommenderData(
    var movieId: String,
    var rating: Int
)