package com.example.comicfantasy.movie.di

import androidx.lifecycle.ViewModel
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.vmfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MovieViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindsComicViewModel(movieViewModel: MovieViewModel): ViewModel

}