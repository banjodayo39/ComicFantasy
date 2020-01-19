package com.example.eBox.di.module

import com.example.eBox.data.local.ApplicationDatabase
import com.example.eBox.data.local.ComicDAO
import com.example.eBox.data.local.GamesDAO
import com.example.eBox.data.local.MovieDAO
import com.example.eBox.data.remote.ComicApiService
import com.example.eBox.data.remote.GamesApiService
import com.example.eBox.data.remote.MovieApiService
import com.example.eBox.data.repo.ComicRepository
import com.example.eBox.data.repo.GamesRepository
import com.example.eBox.data.repo.MovieRepository
import com.example.eBox.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class BoxModule {

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

    @Provides
    internal fun providesComicRepository(apiService: ComicApiService,
                                         comicDao: ComicDAO, provider: SchedulerProvider
    )= ComicRepository(apiService,comicDao,provider)

    @Provides
    internal fun providesComicApi(retrofit: Retrofit): ComicApiService =
        retrofit.create(ComicApiService::class.java)

    @Provides
    internal fun providesComicDao(applicationDatabase: ApplicationDatabase): ComicDAO =
        applicationDatabase.comicDAO()

    @Provides
    internal fun providesGamesRepository(apiService: GamesApiService,
                                         gamesDao: GamesDAO, provider: SchedulerProvider
    )= GamesRepository(apiService,gamesDao,provider)

    @Provides
    internal fun providesGamesApi(retrofit: Retrofit): GamesApiService =
        retrofit.create(GamesApiService::class.java)

    @Provides
    internal fun providesGamesDao(applicationDatabase: ApplicationDatabase): GamesDAO =
        applicationDatabase.gamesDAO()

}