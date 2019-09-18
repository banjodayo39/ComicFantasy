package com.example.comicfantasy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.DataResponse


@Dao
interface ComicDAO {
    @Query("select * from comicEntity")
    fun getAllComics(): DataResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComic(comic: DataResponse)

    @Query("DELETE FROM comicEntity")
    fun clearAllComicTable()

}