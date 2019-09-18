package com.example.comicfantasy.di.module

import android.app.Application
import androidx.room.Room
import com.example.comicfantasy.data.local.ApplicationDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule{

    @Singleton
    @Provides
    internal fun provideApplicationDatabase(context: Application): ApplicationDatabase {
        return Room.databaseBuilder(context, ApplicationDatabase::class.java, "ComicDb")
            .fallbackToDestructiveMigration()
            .build()
    }


}