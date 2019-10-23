package com.example.comicfantasy.data.remote

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movieEntity")
data class MovieDataResponse(
    @PrimaryKey
    @NonNull
    var page: Int = 0,
    @SerializedName("results")
    var results: List<MovieResult?>? = null,
    var total_pages: Int? = null,
    var total_results: Int? = null
):Parcelable

@Parcelize
data class MovieResult(
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    var genre_ids: List<Int?>? = null,
    var id: Int? = null,
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