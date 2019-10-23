package com.example.comicfantasy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.data.remote.Trivia

@Dao
interface GamesDAO {

    @Query("select * from gamesEntity")
    fun getAllTrivia(): Trivia

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addtrivia(trivia: Trivia)

    @Query("DELETE FROM gamesEntity")
    fun clearAllComicTable()
}