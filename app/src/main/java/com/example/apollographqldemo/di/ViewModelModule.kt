package com.example.apollographqldemo.di

import com.example.apollographqldemo.repository.CharRepository
import com.example.apollographqldemo.repository.CharRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepository(charRepository: CharRepositoryImpl): CharRepository
}