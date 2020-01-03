package com.example.comicfantasy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.MovieResult


@Dao
interface MovieDAO {

    @Query("select * from movieEntity")
    fun getAllPopularMovie(): List<MovieResult>

    @Query("select * from movieEntity")
    fun getAllTopRatedMovie(): List<MovieResult>

    @Query("select * from movieEntity")
    fun getUpcomingMovie(): List<MovieResult>

    @Query("select * from movieEntity")
    fun getNowPlaying(): List<MovieResult>

    @Query("select * from movieEntity")
    fun getLatestMovie(): List<MovieResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularMovie(movie: List<MovieResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUpcomingMovie(movie: List<MovieResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTopRatedMovie(movie: List<MovieResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNowPlayingMovie(movie: List<MovieResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLatestMovie(movie: List<MovieResult>)

    @Query("DELETE FROM movieEntity")
    fun clearAllMovieTable()

}