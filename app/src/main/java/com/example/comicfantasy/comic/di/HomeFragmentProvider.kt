package com.example.comicfantasy.home.di

import com.example.comicfantasy.di.module.ViewModelFactoryModule
import com.example.comicfantasy.home.fragments.ComicDetailFragment
import com.example.comicfantasy.home.fragments.ComicFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = [ComicFragmentModule::class,
        ViewModelFactoryModule::class, ComicViewModelModule::class])
    internal abstract fun contributeComicHomeFragment(): ComicFragment

    @ContributesAndroidInjector(modules = [ComicFragmentModule::class,
        ViewModelFactoryModule::class, ComicViewModelModule::class])
    internal abstract fun contributeComicDetailFragment(): ComicDetailFragment

}