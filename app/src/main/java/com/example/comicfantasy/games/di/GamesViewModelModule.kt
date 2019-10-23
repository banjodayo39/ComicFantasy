package com.example.comicfantasy.games.di

import androidx.lifecycle.ViewModel
import com.example.comicfantasy.games.viewmodel.GamesViewModel
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.vmfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
 abstract class GamesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GamesViewModel::class)
    abstract fun bindsGamesModule (gamesViewModel: GamesViewModel): ViewModel
}