package com.example.comicfantasy.home.di

import com.example.comicfantasy.data.local.ApplicationDatabase
import com.example.comicfantasy.data.local.ComicDAO
import com.example.comicfantasy.data.remote.ComicApiService
import com.example.comicfantasy.data.repo.ComicRepository
import com.example.comicfantasy.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HomeFragmentModule {

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

}