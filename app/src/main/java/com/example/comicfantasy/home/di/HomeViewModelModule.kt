package com.example.comicfantasy.home.di

import androidx.lifecycle.ViewModel
import com.example.comicfantasy.home.viewmodel.HomeFragmentViewModel
import com.example.comicfantasy.vmfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindsComicViewModel(homeViewModel: HomeFragmentViewModel): ViewModel


}