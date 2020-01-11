package com.example.comicfantasy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.data.remote.GamesResult
import com.example.comicfantasy.data.remote.Trivia

@Dao
interface GamesDAO {

    @Query("select * from gamesEntity")
    fun getAllTrivia(): List<GamesResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addtrivia(trivia: List<GamesResult>)

    @Query("DELETE FROM gamesEntity")
    fun clearAllComicTable()
}