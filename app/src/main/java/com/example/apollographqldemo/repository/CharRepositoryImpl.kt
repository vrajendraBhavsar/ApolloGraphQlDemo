package com.example.apollographqldemo.repository

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.apollographqldemo.CharactersDataQuery
import com.example.apollographqldemo.network.CharApiService
import javax.inject.Inject

class CharRepositoryImpl @Inject constructor(private val charApiService: CharApiService) : CharRepository {
    override suspend fun getCharList(): Response<CharactersDataQuery.Data> {
        return charApiService.getApolloClient().query(CharactersDataQuery()).await()
    }
}