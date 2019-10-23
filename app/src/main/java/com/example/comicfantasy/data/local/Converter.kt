package com.example.comicfantasy.data.local

import androidx.room.TypeConverter
import com.example.comicfantasy.data.remote.*
import com.google.gson.Gson

class Converter {
    companion object {
        val gson = Gson()


        @TypeConverter
        @JvmStatic
        fun fromDataX(value: Results): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toDataX(value: String): Results {
            return gson.fromJson(value, Results::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromData(value: DataX): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toData(value: String): DataX {
            return gson.fromJson(value, DataX::class.java)
        }


        @TypeConverter
        @JvmStatic
        fun fromResults(value: List<Results>): String {
            return gson.toJson(value)
        }



        @TypeConverter
        @JvmStatic
        fun toResults(value: String): List<Results> {
            return gson.fromJson(value, Array<Results>::class.java).asList()
        }


        @TypeConverter
        @JvmStatic
        fun fromTrivia(trivia: Trivia): String {
            return gson.toJson(trivia)
        }


        @TypeConverter
        @JvmStatic
        fun toTrivia(trivia:String): Trivia {
            return gson.fromJson(trivia,Trivia::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromMovieResults(value: List<MovieResult>): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toMovieResults(value: String): List<MovieResult> {
            return gson.fromJson(value, Array<MovieResult>::class.java).asList()
        }


        @TypeConverter
        @JvmStatic
        fun fromThumbnail(value: Thumbnail): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toThumbnail(value: String): Thumbnail{
            return gson.fromJson(value, Thumbnail::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromGamesResults(games: List<GamesResult>): String {
            return gson.toJson(games)
        }

        @TypeConverter
        @JvmStatic
        fun toGamesResult(value: String): List<GamesResult> {
            return gson.fromJson(value, Array<GamesResult>::class.java).asList()
        }
    }
}