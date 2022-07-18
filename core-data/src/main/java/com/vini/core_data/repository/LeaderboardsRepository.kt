package com.vini.core_data.repository

import com.vini.core_data.model.Resource
import com.vini.core_model.PlayerData
import com.vini.core_model.PlayersLeaderboards
import kotlinx.coroutines.flow.Flow

interface LeaderboardsRepository {
    suspend fun getLeaderboards(): Flow<Resource<List<PlayerData>>>
}