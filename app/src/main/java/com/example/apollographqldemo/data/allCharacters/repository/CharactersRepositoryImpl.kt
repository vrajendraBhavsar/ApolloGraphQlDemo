package com.example.apollographqldemo.data.allCharacters.repository

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.apollographqldemo.domain.allCharacters.CharactersRepository
import com.example.apollographqldemo.data.network.CharApiService
import com.example.apollographqldemozz.CharactersDataQuery
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charApiService: CharApiService) : CharactersRepository {
    override suspend fun getCharList(): Response<CharactersDataQuery.Data> {
        return charApiService.getApolloClient().query(CharactersDataQuery()).await()
    }
}