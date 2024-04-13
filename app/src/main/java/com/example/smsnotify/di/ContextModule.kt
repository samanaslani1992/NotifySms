package com.example.demo.di

import android.content.Context
import com.example.smsnotify.presentation.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }
    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

}