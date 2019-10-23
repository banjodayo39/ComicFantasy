package com.example.comicfantasy.movie.di

import com.example.comicfantasy.data.local.ApplicationDatabase
import com.example.comicfantasy.data.local.ComicDAO
import com.example.comicfantasy.data.local.MovieDAO
import com.example.comicfantasy.data.remote.ComicApiService
import com.example.comicfantasy.data.remote.MovieApiService
import com.example.comicfantasy.data.repo.ComicRepository
import com.example.comicfantasy.data.repo.MovieRepository
import com.example.comicfantasy.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MovieModule {

    @Provides
    internal fun providesMovieRepository(apiService: MovieApiService,
                                         movieDao: MovieDAO, provider: SchedulerProvider
    )= MovieRepository(apiService,movieDao,provider)

    @Provides
    internal fun providesMovieApi(retrofit: Retrofit): MovieApiService =
        retrofit.create(MovieApiService::class.java)

    @Provides
    internal fun providesMovieDao(applicationDatabase: ApplicationDatabase): MovieDAO =
        applicationDatabase.movieDAO()

}