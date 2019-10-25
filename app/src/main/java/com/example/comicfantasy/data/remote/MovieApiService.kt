package com.example.comicfantasy.data.remote

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.http.Url


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

    @POST("movie/{id}/videos")
    fun getTrailers(
        @Url url:String,
        @Path("id") movieId: Int,
        @Query("api_key") apikey: String): Observable<Response<MovieDataResponse>>


}