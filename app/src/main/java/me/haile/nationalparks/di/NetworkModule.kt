package me.haile.nationalparks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.haile.nationalparks.api.NPSService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
//    @Singleton
//    @Provides
//    fun provideUnsplashService(): UnsplashService {
//        return UnsplashService.create()
//    }

    @Singleton
    @Provides
    fun provideNewsApiService(): NPSService {
        return NPSService.create()
    }
}