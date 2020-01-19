package com.example.eBox.di.builder

import com.example.eBox.home.ui.HomeActivity
import com.example.eBox.di.module.ViewModelFactoryModule
import com.example.eBox.ui.di.HomeFragmentProvider
import com.example.eBox.di.module.BoxModule
import com.example.eBox.di.module.BoxViewModelModule
import com.example.eBox.movie.fragment.MovieDetailActivity
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