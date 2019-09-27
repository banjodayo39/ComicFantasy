package com.example.comicfantasy.home.di

import com.example.comicfantasy.di.module.ViewModelFactoryModule
import com.example.comicfantasy.home.fragments.ComicFragment
import com.example.comicfantasy.series.fragment.SeriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class,
        ViewModelFactoryModule::class, HomeViewModelModule::class])
    internal abstract fun contributeComicHomeFragment(): ComicFragment



    @ContributesAndroidInjector(modules = [HomeFragmentModule::class,
        ViewModelFactoryModule::class, HomeViewModelModule::class])
    internal abstract fun contributeComicSeriesFragment(): SeriesFragment

}