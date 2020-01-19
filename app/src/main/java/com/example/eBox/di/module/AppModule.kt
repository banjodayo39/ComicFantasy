package com.example.eBox.di.module

import android.app.Application
import androidx.room.Room
import com.example.eBox.data.local.ApplicationDatabase
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