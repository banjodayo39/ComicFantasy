package com.example.comicfantasy.di.builder

import com.example.comicfantasy.home.ui.HomeActivity
import com.example.comicfantasy.di.module.ViewModelFactoryModule
import com.example.comicfantasy.comic.di.HomeFragmentProvider
import com.example.comicfantasy.di.module.BoxModule
import com.example.comicfantasy.di.module.BoxViewModelModule
import com.example.comicfantasy.movie.fragment.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeFragmentProvider::class,BoxModule::class,
            ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeMainActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [HomeFragmentProvider::class, BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeMovieActivty(): MovieDetailActivity

}