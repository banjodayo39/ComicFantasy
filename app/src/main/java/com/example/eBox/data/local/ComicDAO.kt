package com.example.eBox.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eBox.data.remote.ComicResults


@Dao
interface ComicDAO {
    @Query("select * from comicEntity")
    fun getAllComics(): List<ComicResults>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComic(comic:List<ComicResults>)

    @Query("DELETE FROM comicEntity")
    fun clearAllComicTable()

}