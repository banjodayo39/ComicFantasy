package com.example.comicfantasy.data.remote

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gamesEntity")
data class Trivia(
    @PrimaryKey
    @NonNull
    var response_code: Int = 0,
    @SerializedName("results")
    var results: List<GamesResult?>? = null
):Parcelable


@Parcelize
data class GamesResult(
    var category: String? = null,
    var correct_answer: String? = null,
    var difficulty: String? = null,
    var incorrect_answers: List<String?>? = null,
    var question: String? = null,
    var type: String? = null

):Parcelable