package com.vini.core_data.domain

import android.util.Log
import com.vini.core_data.model.ApiResult
import com.vini.core_data.repository.LeaderboardsRepository
import com.vini.core_model.PlayerData
import com.vini.core_model.data.local.LeaderboardPlayerDao
import com.vini.core_model.data.local.LorLeaderboardPlayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LeaderboardsUseCase @Inject constructor(
    private val repository: LeaderboardsRepository,
) {
    suspend operator fun invoke(): ApiResult<List<PlayerData>> {
        return try {
            val cachedListPlayer = repository.getCachedLeaderboards()
            if (cachedListPlayer.isEmpty()) {
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