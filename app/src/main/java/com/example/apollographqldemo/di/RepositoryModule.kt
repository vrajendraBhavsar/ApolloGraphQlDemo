package com.example.retrofit_hilt.di

import com.example.apollographqldemo.data.network.CharApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Singleton ..here we will provide all 3rd party library Obj..
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNetworkServices(): CharApiService {
        return CharApiService()
    }
}
