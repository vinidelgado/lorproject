package com.vini.core_data.repository

import androidx.annotation.WorkerThread
import com.vini.core_model.PlayerData
import com.vini.core_model.data.local.LeaderboardConfigDao
import com.vini.core_model.data.local.LeaderboardPlayerDao
import com.vini.core_model.data.local.LorLeaderboardConfig
import com.vini.core_model.data.local.LorLeaderboardPlayer
import com.vini.core_network.retrofit.LeaderboardApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class LeaderboardsRepository @Inject constructor(
    private val api: LeaderboardApi,
    private val leaderboardPlayerDao: LeaderboardPlayerDao,
    private val leaderboardConfigDao: LeaderboardConfigDao,
) {
    suspend fun getLeaderboards(): List<PlayerData> = api.getLeaderboards().playersData

    @WorkerThread
    fun getCachedLeaderboards() = leaderboardPlayerDao.getAll()

    @WorkerThread
    suspend fun addPlayerList(playerList: List<LorLeaderboardPlayer>) =
        leaderboardPlayerDao.insertAllPlayers(playerList)

    @WorkerThread
    suspend fun addConfig(config: String) {
        leaderboardConfigDao.deleteAll()
        leaderboardConfigDao.insertConfig(LorLeaderboardConfig(lastUpdate = config))
    }

    @WorkerThread
    fun getConfig() =
        leaderboardConfigDao.getAll()
}

