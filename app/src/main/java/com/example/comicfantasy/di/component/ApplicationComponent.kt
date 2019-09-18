package com.example.comicfantasy.di.component

import android.app.Application
import com.example.comicfantasy.base.BaseApplication
import com.example.comicfantasy.di.builder.ActivityBuilder
import com.example.comicfantasy.di.module.AppModule
import com.example.comicfantasy.di.module.ContextModule
import com.example.comicfantasy.di.module.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton



@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ContextModule::class,
    ActivityBuilder::class,
    RetrofitModule::class])
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(application: BaseApplication)



}
