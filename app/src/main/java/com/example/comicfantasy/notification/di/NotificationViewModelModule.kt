package com.example.comicfantasy.notification.di

import androidx.lifecycle.ViewModel
import com.example.comicfantasy.notification.viewmodel.NotificationViewModel
import com.example.comicfantasy.vmfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NotificationViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun bindsNotificationViewModel(notificationViewModel:NotificationViewModel): ViewModel


}