package com.example.apollographqldemo.domain.singleCharacter

import com.apollographql.apollo.api.Response
import com.example.apollographqldemozz.SingleCharacterDataQuery

interface SingleCharacterRepository {
    suspend fun getSingleChar(id : String): Response<SingleCharacterDataQuery.Data>
}