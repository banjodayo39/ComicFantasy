package com.example.comicfantasy.data.remote

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import okhttp3.ResponseBody
import retrofit2.http.POST


interface MovieApiService {
    //private val MOVIE_BASE_URL = "http://api.themoviedb.org/3/"

    @POST
    fun getPopularMovie(
        @Url url: String ,
        @Query("api_key") apikey:String
    ): Observable<Response<MovieDataResponse>>


    @GET("movie/top_rated")
    fun getTopRated(

        @Query("api_key") apikey:String
    ): Observable<Response<MovieDataResponse>>

    @GET
    fun profilePicture(@Url url: String): Call<ResponseBody>

}