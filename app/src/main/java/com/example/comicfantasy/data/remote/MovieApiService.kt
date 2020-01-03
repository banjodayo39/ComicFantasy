package com.example.comicfantasy.data.remote

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.http.Url


interface MovieApiService {
    //private val MOVIE_BASE_URL = "http://api.themoviedb.org/3/"
     //https://developers.themoviedb.org/3/movies/get-movie-videos

    @POST
    fun getMovie(
        @Url url: String ,
        @Query("api_key") apikey:String
    ): Observable<Response<MovieDataResponse>>


    @POST
    fun getTopRatedMovie(
        @Url url: String ,
        @Query("api_key") apikey:String
    ): Observable<Response<MovieDataResponse>>

    @GET
    fun profilePicture(@Url url: String): Call<ResponseBody>

    @GET
    fun getTrailers(
        @Url url:String,
        //@Path("id") movieId: Int,
        @Query("api_key") apikey: String ): Observable<Response<Trailer>>


}