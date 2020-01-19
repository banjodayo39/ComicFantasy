package com.example.comicfantasy.data.remote

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Trivia(
    @PrimaryKey
    @NonNull
    var response_code: Int = 0,
    @SerializedName("results")
    var results: List<GamesResult>? = null
)

@Parcelize
@Entity(tableName = "gamesEntity")
data class GamesResult(
    @PrimaryKey
    @NonNull
    var category: String = "",
    @SerializedName("correct_answer")
    var correct_answer: String? = null,
    @SerializedName("difficulty")
    var difficulty: String? = null,
    @SerializedName("incorrect_answers")
    var incorrect_answers: List<String?>? = null,
    var question: String? = null,
    var type: String? = null,
    var optionSelected: String = ""

):Parcelable