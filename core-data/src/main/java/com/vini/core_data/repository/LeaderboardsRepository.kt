package com.vini.core_data.repository

import com.vini.core_network.retrofit.LeaderboardApi
import javax.inject.Inject

class LeaderboardsRepository @Inject constructor(
    private val api: LeaderboardApi
) {
    suspend fun getLeaderboards() = api.getLeaderboards().playersData
}