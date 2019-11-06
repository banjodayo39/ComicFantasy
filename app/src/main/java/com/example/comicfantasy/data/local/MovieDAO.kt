package com.example.comicfantasy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.MovieDataResponse
import com.example.comicfantasy.data.remote.MovieResult


@Dao
interface MovieDAO {

    @Query("select * from movieEntity")
    fun getAllMovie(): List<MovieResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: List<MovieResult>)

    @Query("DELETE FROM movieEntity")
    fun clearAllMovieTable()


}