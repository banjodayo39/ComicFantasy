package com.example.comicfantasy.data.remote

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.graphics.Movie
import com.google.gson.annotations.Expose


@Parcelize
data class MovieDataResponse(

    var page: Int = 0,
    @SerializedName("results")
    var results: List<MovieResult?>? = null,
    var title: String ="",
    var total_pages: Int? = null,
    var total_results: Int? = null
    //var trailer: Trailer?=null
):Parcelable


data class Data(
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    var belongs_to_collection: Any? = null,
    var budget: Int? = null,
    var genres: List<Genre?>? = null,
    var homepage: String? = null,
    var id: Int? = null,
    var imdb_id: String? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var production_companies: List<ProductionCompany?>? = null,
    var production_countries: List<ProductionCountry?>? = null,
    var release_date: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var spoken_languages: List<SpokenLanguage?>? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null
)

data class Genre(
    var id: Int? = null,
    var name: String? = null
)

data class ProductionCompany(
    var id: Int? = null,
    var logo_path: String? = null,
    var name: String? = null,
    var origin_country: String? = null
)

data class ProductionCountry(
    var iso_3166_1: String? = null,
    var name: String? = null
)

data class SpokenLanguage(
    var iso_639_1: String? = null,
    var name: String? = null
)
@Parcelize
@Entity(tableName = "movieEntity")
data class MovieResult(
    @PrimaryKey
    @NonNull
    var id: Int = 0,
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    var genre_ids: List<Int?>? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null


):Parcelable



@Parcelize
data class Trailer(
    var id: Int? = null,
    var results: List<TrailerResult?>? = null
):Parcelable

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
):Parcelable

data class MovieRecommenderData(
    var movieId: String,
    var rating: Int
)