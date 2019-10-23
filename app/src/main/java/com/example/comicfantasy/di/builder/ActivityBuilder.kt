package com.example.comicfantasy.di.builder

import com.example.comicfantasy.home.ui.HomeActivity
import com.example.comicfantasy.di.module.ViewModelFactoryModule
import com.example.comicfantasy.comic.di.ComicFragmentModule
import com.example.comicfantasy.comic.di.HomeFragmentProvider
import com.example.comicfantasy.comic.di.ComicViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeFragmentProvider::class,ComicFragmentModule::class,
            ViewModelFactoryModule::class, ComicViewModelModule::class])
    internal abstract fun contributeMainActivity(): HomeActivity

}