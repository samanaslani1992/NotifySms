package com.example.demo.di

import com.example.smsnotify.data.repository.LocalStorage.LocalStorageRepository
import com.example.smsnotify.data.repository.LocalStorage.LocalStorageRepositoryImpl
import com.example.smsnotify.data.repository.device.DeviceRepository
import com.example.smsnotify.data.repository.device.DeviceRepositoryImpl
import com.example.smsnotify.presentation.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDeviceRepository(app: App): DeviceRepository {
        return DeviceRepositoryImpl(app)
    }

    @Provides
    @Singleton
    fun provideLocalStorageRepository(app: App): LocalStorageRepository {
        return LocalStorageRepositoryImpl(app)
    }
}