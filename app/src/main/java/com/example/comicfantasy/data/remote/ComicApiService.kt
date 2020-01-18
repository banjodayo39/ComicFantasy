package com.example.comicfantasy.data.remote

import com.example.comicfantasy.util.DataResp
import com.example.comicfantasy.util.PaginatedResp
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface ComicApiService {

    @GET
    fun fetchListOfComic(
        @Url url: String,
        @Query("ts") timestamp: String,
        @Query("apikey") apikey:String,
        @Query("hash") hashSignture:String
    ): Observable<Response<DataResp<ComicResponse>>>

    @GET
    fun getMovie(
        @Url url: String,
        @Query("api_key") apikey:String
    ): Observable<Response<PaginatedResp<MovieData>>>


    @GET("/v1/public/comics/{comicId}/stories")
    fun fetchComicStory(
        @Path("id") id:Int,
        @Query("ts") timestamp: String,
        @Query("api_key") apikey:String,
        @Query("hash") hashSignture:String
    ): Observable<Response<ComicResults>>



}