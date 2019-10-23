package com.example.comicfantasy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.data.remote.MovieDataResponse
import com.example.comicfantasy.data.remote.Trivia

@Database(entities = [MovieDataResponse::class,
    DataResponse::class,Trivia::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun comicDAO():ComicDAO
    abstract fun movieDAO():MovieDAO
    abstract fun gamesDAO():GamesDAO

}