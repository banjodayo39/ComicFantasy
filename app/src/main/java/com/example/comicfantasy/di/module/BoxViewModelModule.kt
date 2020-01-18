package com.example.comicfantasy.di.module

import androidx.lifecycle.ViewModel
import com.example.comicfantasy.comic.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.games.viewmodel.GamesViewModel
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.vmfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class BoxViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindsMovieViewModel(movieViewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ComicFragmentViewModel::class)
    abstract fun bindsComicViewModel(comicViewModel: ComicFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GamesViewModel::class)
    abstract fun bindsGamesModule (gamesViewModel: GamesViewModel): ViewModel

}