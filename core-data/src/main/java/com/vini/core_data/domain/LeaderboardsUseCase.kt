package com.vini.core_data.domain

import android.util.Log
import com.vini.core_data.model.ApiResult
import com.vini.core_data.repository.LeaderboardsRepository
import com.vini.core_model.model.leaderboards.PlayerData
import com.vini.core_model.data.local.LorLeaderboardPlayer
import retrofit2.HttpException
import java.io.IOException
import java.lang.NullPointerException
import javax.inject.Inject

class LeaderboardsUseCase @Inject constructor(
    private val repository: LeaderboardsRepository,
) {
    suspend operator fun invoke(): ApiResult<List<PlayerData>> {
        return try {
            val cachedListPlayer = repository.getCachedLeaderboards()
            if (needUpdate(cachedListPlayer)) {
                repository.deleteAllPlayerList()
                repository.addConfig(System.currentTimeMillis().toString())
                val leaderboards = repository.getLeaderboards()
                repository.addPlayerList(playerDataToLorLeaderboardPlayer(leaderboards))
                ApiResult.Success(leaderboards)
            } else {
                ApiResult.Success(lorLeaderboardPlayerToPlayerData(cachedListPlayer))
            }
        } catch (e: HttpException) {
            ApiResult.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            ApiResult.Error(exception = "Couldn't reach server. Check your internet connection.")
        } catch (e: Exception) {
            Log.d("Nelson", e.message ?: "Erro desconhecido")
            ApiResult.Error(exception = "Server problems")
        }
    }

    private fun needUpdate(cachedListPlayer: List<LorLeaderboardPlayer>): Boolean {
        return try {
            val lastUpdate = repository.getConfig().lastUpdate.toLong()
            val dataExpired = lastUpdate < (System.currentTimeMillis() + (1000 * 60 * 60 * 24))
            val emptyList = cachedListPlayer.isEmpty()
            dataExpired || emptyList
        } catch (e: NullPointerException) {
            true
        }

    }

    private fun lorLeaderboardPlayerToPlayerData(cachedListPlayer: List<LorLeaderboardPlayer>): List<PlayerData> {
        val listPlayer = ArrayList<PlayerData>()
        cachedListPlayer.forEach {
            listPlayer.add(PlayerData(name = it.name, rank = it.rank, lp = it.lp))
        }
        return listPlayer
    }

    private fun playerDataToLorLeaderboardPlayer(playerData: List<PlayerData>): List<LorLeaderboardPlayer> {
        val listPlayer = ArrayList<LorLeaderboardPlayer>()
        playerData.forEach {
            listPlayer.add(LorLeaderboardPlayer(name = it.name, rank = it.rank, lp = it.lp))
        }
        return listPlayer
    }
}