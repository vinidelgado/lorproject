package com.vini.core_network.retrofit

import com.vini.core_model.model.meta_decks.MetaDecks
import com.vini.core_model.model.leaderboards.PlayersLeaderboards
import retrofit2.http.GET

interface LeaderboardApi {
    @GET("lor/ranked/v1/leaderboards")
    suspend fun getLeaderboards(): PlayersLeaderboards

    @GET("lor/ranked/v1/")
    suspend fun getMetaDecks(): MetaDecks
}