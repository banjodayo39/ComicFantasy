package com.example.comicfantasy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.MovieDataResponse


@Dao
interface MovieDAO {

    @Query("select * from movieEntity")
    fun getAllMovie(): MovieDataResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: MovieDataResponse)

    @Query("DELETE FROM movieEntity")
    fun clearAllMovieTable()
}