package com.example.eBox.data.remote

import com.example.eBox.util.PaginatedResp
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.http.Url


interface MovieApiService {
    //private val MOVIE_BASE_URL = "http://api.themoviedb.org/3/"
     //https://developers.themoviedb.org/3/movies/get-movie-videos
    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1

    @GET("/3/movie/popular")
    fun getMovie(
        @Query("api_key") apikey:String
    ): Observable<Response<PaginatedResp<PopularMovie>>>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") apikey:String
    ): Observable<Response<PaginatedResp<TopRatedMovie>>>

    @GET("/3/movie/now_playing")
    fun getNowPlayingMovie(
        @Query("api_key") apikey:String
    ): Observable<Response<PaginatedResp<NowShowing>>>

    @GET("/3/movie/latest")
    fun getLatestMovie(
        @Query("api_key") apikey:String
    ): Observable<Response<Latest>>

    @GET("/3/movie/upcoming")
    fun getUpComingMovie(
        @Query("api_key") apikey:String
    ): Observable<Response<PaginatedResp<UpComing>>>

    @GET
    fun profilePicture(@Url url: String): Call<ResponseBody>

    @GET("/3/movie/{movie_id}/videos")
    fun getTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apikey: String
    ): Observable<Response<Trailer>>

    @GET
    fun getTrailers(
        @Url url:String,
        //@Path("id") movieId: Int,
        @Query("api_key") apikey: String ): Observable<Response<Trailer>>


}