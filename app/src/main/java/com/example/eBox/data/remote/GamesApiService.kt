package com.example.eBox.data.remote

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Url

interface GamesApiService {

    @POST
    fun getTrivia(
        @Url url: String
    ): Observable<Response<Trivia>>



}