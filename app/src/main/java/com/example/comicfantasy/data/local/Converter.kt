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
        fun toCharacters(character:String): Characters{
            return gson.fromJson(character,Characters::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromCharacters(value: Characters): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toCreators(creators:String): Creators{
            return gson.fromJson(creators,Creators::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromCreators(value: Creators): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun fromImage(value: List<Image>): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toImage(images:String): List<Image>{
            return gson.fromJson(images,Array<Image>::class.java).asList()
        }


        @TypeConverter
        @JvmStatic
        fun toStory(story:String): Stories{
            return gson.fromJson(story,Stories::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromStory(story: Stories): String {
            return gson.toJson(story)
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

        @TypeConverter
        @JvmStatic
        fun fromTrailer(value: Trailer): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toTrailer(value: String): Trailer{
            return gson.fromJson(value, Trailer::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun toEvents(events:String): Events{
            return gson.fromJson(events,Events::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromEvents(value: Events): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toSeries(series:String): Series{
            return gson.fromJson(series,Series::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromSeries(value: Series): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toMovieResult(movieResult:String): MovieResult{
            return gson.fromJson(movieResult,MovieResult::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromMovieResult(value: MovieResult): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun fromGenre(genre: List<Int>): String {
            return gson.toJson(genre)
        }

        @TypeConverter
        @JvmStatic
        fun toGenre(value: String): List<Int> {
            return gson.fromJson(value, Array<Int>::class.java).asList()
        }



    }
}