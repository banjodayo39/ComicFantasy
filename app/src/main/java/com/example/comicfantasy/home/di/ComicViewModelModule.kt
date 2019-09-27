package com.example.comicfantasy.home.di

import androidx.lifecycle.ViewModel
import com.example.comicfantasy.home.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.vmfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ComicViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ComicFragmentViewModel::class)
    abstract fun bindsComicViewModel(comicViewModel: ComicFragmentViewModel): ViewModel


}