package com.example.demo.di

import com.example.smsnotify.data.repository.LocalStorage.LocalStorageRepository
import com.example.smsnotify.data.repository.device.DeviceRepository
import com.example.smsnotify.domain.useCase.SmsUseCase
import com.example.smsnotify.domain.useCase.SmsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideSmsUseCase(
        deviceRepository: DeviceRepository,
        localStorageRepository: LocalStorageRepository
    ): SmsUseCase {
        return SmsUseCaseImpl(deviceRepository, localStorageRepository)
    }
}