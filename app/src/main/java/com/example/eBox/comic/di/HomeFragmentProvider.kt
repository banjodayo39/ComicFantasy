package com.example.eBox.comic.di

import com.example.eBox.comic.fragments.*
import com.example.eBox.di.module.BoxModule
import com.example.eBox.di.module.BoxViewModelModule
import com.example.eBox.di.module.ViewModelFactoryModule
import com.example.eBox.games.fragments.GamesFragment
import com.example.eBox.movie.fragment.MovieDetailFragment
import com.example.eBox.movie.fragment.MovieFragment
import com.example.eBox.movie.fragment.TrailerFragment
import com.example.eBox.notification.fragment.NotificationFragment
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