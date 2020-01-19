package com.example.eBox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eBox.data.remote.*


@Dao
interface MovieDAO {

    @Query("select * from popular")
    fun getAllPopularMovie(): List<PopularMovie>

    @Query("select * from topRated")
    fun getAllTopRatedMovie(): List<TopRatedMovie>

    @Query("select * from upcoming")
    fun getUpcomingMovie(): List<UpComing>

    @Query("select * from now_showing")
    fun getNowPlaying(): List<NowShowing>

    @Query("select * from latest")
    fun getLatestMovie(): Latest

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularMovie(movie: PopularMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUpcomingMovie(movie: UpComing)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTopRatedMovie(movie: List<TopRatedMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNowPlayingMovie(movie: NowShowing)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLatestMovie(movie: Latest)

    @Query("DELETE FROM popular")
    fun clearAllMovieTable()

}