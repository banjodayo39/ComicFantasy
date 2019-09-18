package com.example.comicfantasy.di.builder

import com.example.comicfantasy.HomeActivity
import com.example.comicfantasy.di.module.ViewModelFactoryModule
import com.example.comicfantasy.home.di.HomeFragmentModule
import com.example.comicfantasy.home.di.HomeFragmentProvider
import com.example.comicfantasy.home.di.HomeViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeFragmentProvider::class,HomeFragmentModule::class,
            ViewModelFactoryModule::class, HomeViewModelModule::class])
    internal abstract fun contributeMainActivity(): HomeActivity

}