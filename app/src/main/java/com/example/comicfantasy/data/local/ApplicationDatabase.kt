package com.example.comicfantasy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.comicfantasy.data.remote.*

@Database(entities = [MovieResult::class,
    Results::class,Trivia::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun comicDAO():ComicDAO
    abstract fun movieDAO():MovieDAO
    abstract fun gamesDAO():GamesDAO

}