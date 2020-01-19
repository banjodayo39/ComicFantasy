package com.example.eBox.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eBox.data.remote.*

@Database(
    entities = [PopularMovie::class,
        TopRatedMovie::class,
        Latest::class,
        NowShowing::class,
        UpComing::class,
        ComicResults::class, GamesResult::class], version = 1, exportSchema = false
)
@TypeConverters(Converter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun comicDAO(): ComicDAO
    abstract fun movieDAO(): MovieDAO
    abstract fun gamesDAO(): GamesDAO

}