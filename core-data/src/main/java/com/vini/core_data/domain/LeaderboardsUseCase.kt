package com.vini.core_data.domain

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
    private val leaderboardPlayerDao: LeaderboardPlayerDao,
    private val repository: LeaderboardsRepository
) {
    operator fun invoke(): Flow<ApiResult<List<PlayerData>>> = flow {
        try {
            emit(ApiResult.Loading(null))
            //TODO: Wip, remove next lines, only to test
            val players = repository.getLeaderboards()
            val listDao = ArrayList<LorLeaderboardPlayer>()
            players.forEach {
                listDao.add(
                    LorLeaderboardPlayer(
                        name = it.name,
                        rank = it.rank,
                        lp = it.lp
                    )
                )
            }
            if (listDao.isNotEmpty()) {
                leaderboardPlayerDao.insertAllPlayers(listDao)
            }
            emit(ApiResult.Success(players))
        } catch (e: HttpException) {
            emit(ApiResult.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(ApiResult.Error(exception = "Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(ApiResult.Error(exception = "Server problems"))
        }
    }
}