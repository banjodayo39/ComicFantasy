package com.example.comicfantasy.data.remote

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ComicApiService {

    @GET("/v1/public/comics")
    fun fetchListOfComicThumbnail(
        @Query("ts") timestamp: String,
        @Query("apikey") apikey:String,
        @Query("hash") hashSignture:String
    ): Observable<Response<DataResponse>>


    @GET("/v1/public/comics/{comicId}")
    fun fetchListOfComicImages(
        @Path("comicId") comicId :Int?,
        @Query("ts") timestamp: String,
        @Query("apikey") apikey:String,
        @Query("hash") hashSignture:String
    ): Observable<Response<DataResponse>>

}