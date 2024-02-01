package ru.radzze.effective_mobile.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.radzze.effective_mobile.data.UserStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideUserStore(application: Application): UserStore {
        return UserStore(application)
    }
}