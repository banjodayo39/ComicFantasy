package com.example.comicfantasy.comic.di

import com.example.comicfantasy.comic.fragments.*
import com.example.comicfantasy.di.module.ViewModelFactoryModule
import com.example.comicfantasy.games.di.GamesModule
import com.example.comicfantasy.games.di.GamesViewModelModule
import com.example.comicfantasy.games.fragments.GamesFragment
import com.example.comicfantasy.movie.di.MovieModule
import com.example.comicfantasy.movie.di.MovieViewModelModule
import com.example.comicfantasy.movie.fragment.MovieDetailActivity
import com.example.comicfantasy.movie.fragment.MovieDetailFragment
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.example.comicfantasy.movie.fragment.TrailerFragment
import com.example.comicfantasy.notification.di.NotificationModule
import com.example.comicfantasy.notification.di.NotificationViewModelModule
import com.example.comicfantasy.notification.fragment.NotificationFragment
import com.example.comicfantasy.notification.viewmodel.NotificationViewModel
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

    @ContributesAndroidInjector(modules = [ComicFragmentModule::class,
        ViewModelFactoryModule::class, ComicViewModelModule::class])
    internal abstract fun contributeStoryTabFragment():StoryTabFragment

    @ContributesAndroidInjector(modules = [ComicFragmentModule::class,
        ViewModelFactoryModule::class, ComicViewModelModule::class])
    internal abstract fun contributeCharacterTabFragment(): CharacterTabFragment
/*

    @ContributesAndroidInjector(modules = [ComicFragmentModule::class,
        ViewModelFactoryModule::class, ComicViewModelModule::class])
    internal abstract fun contributeCreatorTabFragment(): CreatorTabFragment

    @ContributesAndroidInjector(modules = [ComicFragmentModule::class,
        ViewModelFactoryModule::class, ComicViewModelModule::class])
    internal abstract fun contributeEventTabFragment(): EventTabFragment
*/


    @ContributesAndroidInjector(modules = [MovieModule::class,
        ViewModelFactoryModule::class, MovieViewModelModule::class])
    internal abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector(modules = [MovieModule::class,
        ViewModelFactoryModule::class, MovieViewModelModule::class])
    internal abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector(modules = [GamesModule::class,
        ViewModelFactoryModule::class, GamesViewModelModule::class])
    internal abstract fun contributeGamesFragment(): GamesFragment

    @ContributesAndroidInjector(modules = [MovieModule::class,
        ViewModelFactoryModule::class, MovieViewModelModule::class])
    internal abstract fun contributeTrailerFragment(): TrailerFragment

   @ContributesAndroidInjector(modules = [NotificationModule::class,
   ViewModelFactoryModule::class, NotificationViewModelModule::class])
   internal abstract fun contributeNotificationFragment():NotificationFragment


}