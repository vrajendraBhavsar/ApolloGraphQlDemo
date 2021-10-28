package com.example.apollographqldemo.repository

import com.apollographql.apollo.api.Response
import com.example.apollographqldemo.CharactersDataQuery

interface CharRepository {
    suspend fun getCharList(): Response<CharactersDataQuery.Data>
}