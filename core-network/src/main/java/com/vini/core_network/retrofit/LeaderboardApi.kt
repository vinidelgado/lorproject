package com.vini.core_network.retrofit

import com.vini.core_model.PlayersLeaderboards
import retrofit2.http.GET

interface LeaderboardApi {
    @GET("lor/ranked/v1/leaderboards")
    suspend fun getLeaderboards(): PlayersLeaderboards
}