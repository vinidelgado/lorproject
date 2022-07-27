package com.vini.core_data.repository

import com.vini.core_model.model.meta_decks.MetaData
import com.vini.core_network.retrofit.LeaderboardApi
import javax.inject.Inject

class MetaRepository @Inject constructor(
    private val api: LeaderboardApi,
) {
    suspend fun getMetaDecks(): List<MetaData> = api.getMetaDecks().deckList
}

