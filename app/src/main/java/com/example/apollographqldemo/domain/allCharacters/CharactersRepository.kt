package com.example.apollographqldemo.domain.allCharacters

import com.apollographql.apollo.api.Response
import com.example.apollographqldemozz.CharactersDataQuery

interface CharactersRepository {
    suspend fun getCharList(): Response<CharactersDataQuery.Data>
}