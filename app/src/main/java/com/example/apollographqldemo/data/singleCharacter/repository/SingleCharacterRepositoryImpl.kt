package com.example.apollographqldemo.data.singleCharacter.repository

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.apollographqldemo.data.network.CharApiService
import com.example.apollographqldemo.domain.singleCharacter.SingleCharacterRepository
import com.example.apollographqldemozz.SingleCharacterDataQuery
import javax.inject.Inject

class SingleCharacterRepositoryImpl @Inject constructor(private val charApiService: CharApiService) : SingleCharacterRepository {
    override suspend fun getSingleChar(id: String): Response<SingleCharacterDataQuery.Data> {
        return charApiService.getApolloClient().query(SingleCharacterDataQuery(id = id)).await()
    }
}