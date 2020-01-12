package com.example.comicfantasy.data.remote

import com.example.comicfantasy.util.DataResp
import com.example.comicfantasy.util.ListResp
import com.example.comicfantasy.util.PaginatedResp
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ComicApiService {

    @GET("/v1/public/comics")
    fun fetchListOfComic(
        @Query("ts") timestamp: String,
        @Query("apikey") apikey:String,
        @Query("hash") hashSignture:String
    ): Observable<Response<DataResp<ComicResponse>>>


    @GET("/v1/public/comics/{comicId}/stories")
    fun fetchComicStory(
        @Path("id") id:Int,
        @Query("ts") timestamp: String,
        @Query("apikey") apikey:String,
        @Query("hash") hashSignture:String
    ): Observable<Response<ComicResults>>



}