package com.example.comicfantasy.data.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.ComicResponse
import com.example.comicfantasy.data.remote.ComicResults


@Dao
interface ComicDAO {
    @Query("select * from comicEntity")
    fun getAllComics(): List<ComicResults>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComic(comic:List<ComicResults>)

    @Query("DELETE FROM comicEntity")
    fun clearAllComicTable()

}