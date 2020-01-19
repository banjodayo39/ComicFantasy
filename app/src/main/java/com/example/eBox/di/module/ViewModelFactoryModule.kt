package com.example.eBox.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.eBox.vmfactory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}