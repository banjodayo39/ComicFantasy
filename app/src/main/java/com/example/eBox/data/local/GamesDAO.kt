package com.example.eBox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eBox.data.remote.GamesResult

@Dao
interface GamesDAO {

    @Query("select * from gamesEntity")
    fun getAllTrivia(): List<GamesResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addtrivia(trivia: List<GamesResult>)

    @Query("DELETE FROM gamesEntity")
    fun clearAllComicTable()
}