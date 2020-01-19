package com.example.eBox.base

import com.example.eBox.di.component.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {


    private val component = DaggerApplicationComponent.builder()
        .application(this)
        .build()
    override fun applicationInjector()=component

}