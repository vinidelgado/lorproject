package com.vini.core_network.retrofit

import com.vini.core_model.model.meta_decks.MetaDecks
import com.vini.core_model.model.leaderboards.PlayersLeaderboards
import com.vini.core_model.model.news.News
import retrofit2.http.GET

interface LeaderboardApi {
    @GET("lor/ranked/v1/leaderboards")
    suspend fun getLeaderboards(): PlayersLeaderboards

    @GET("lor/ranked/v1/meta")
    suspend fun getMetaDecks(): MetaDecks

    @GET("lor/ranked/v1/news")
    suspend fun getNews(): News
}