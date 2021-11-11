package com.example.apollographqldemo.di

import com.example.apollographqldemo.domain.allCharacters.CharactersRepository
import com.example.apollographqldemo.data.allCharacters.repository.CharactersRepositoryImpl
import com.example.apollographqldemo.data.singleCharacter.repository.SingleCharacterRepositoryImpl
import com.example.apollographqldemo.domain.singleCharacter.SingleCharacterRepository
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
    abstract fun bindRepository(charRepository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCharacterRepository(singleCharRepositoryImpl: SingleCharacterRepositoryImpl): SingleCharacterRepository
}