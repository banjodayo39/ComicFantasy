package com.example.comicfantasy.comic.di

import com.example.comicfantasy.comic.fragments.*
import com.example.comicfantasy.di.module.BoxModule
import com.example.comicfantasy.di.module.BoxViewModelModule
import com.example.comicfantasy.di.module.ViewModelFactoryModule
import com.example.comicfantasy.games.fragments.GamesFragment
import com.example.comicfantasy.movie.fragment.MovieDetailFragment
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.example.comicfantasy.movie.fragment.TrailerFragment
import com.example.comicfantasy.notification.di.NotificationModule
import com.example.comicfantasy.notification.di.NotificationViewModelModule
import com.example.comicfantasy.notification.fragment.NotificationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeComicHomeFragment(): ComicFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeComicDetailFragment(): ComicDetailFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeStoryTabFragment():StoryTabFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeCharacterTabFragment(): CharacterTabFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeGamesFragment(): GamesFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeTrailerFragment(): TrailerFragment

    @ContributesAndroidInjector(modules = [BoxModule::class,
        ViewModelFactoryModule::class, BoxViewModelModule::class])
    internal abstract fun contributeNotificationFragment(): NotificationFragment


}