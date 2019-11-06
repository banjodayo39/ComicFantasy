package com.example.comicfantasy.data.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicfantasy.data.remote.DataX
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.util.DataResp
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response


@Dao
interface ComicDAO {
    @Query("select * from comicEntity")
    fun getAllComics(): List<Results>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComic(comic: List<Results>)

    @Query("DELETE FROM comicEntity")
    fun clearAllComicTable()

}