package com.example.comicfantasy.games.di

import com.example.comicfantasy.data.local.ApplicationDatabase
import com.example.comicfantasy.data.local.GamesDAO
import com.example.comicfantasy.data.local.MovieDAO
import com.example.comicfantasy.data.remote.GamesApiService
import com.example.comicfantasy.data.remote.MovieApiService
import com.example.comicfantasy.data.repo.GamesRepository
import com.example.comicfantasy.data.repo.MovieRepository
import com.example.comicfantasy.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class GamesModule {

    @Provides
    internal fun providesGamesRepository(apiService: GamesApiService,
                                         gamesDao:GamesDAO, provider: SchedulerProvider
    )= GamesRepository(apiService,gamesDao,provider)

    @Provides
    internal fun providesGamesApi(retrofit: Retrofit): GamesApiService =
        retrofit.create(GamesApiService::class.java)

    @Provides
    internal fun providesGamesDao(applicationDatabase: ApplicationDatabase):GamesDAO =
        applicationDatabase.gamesDAO()

}