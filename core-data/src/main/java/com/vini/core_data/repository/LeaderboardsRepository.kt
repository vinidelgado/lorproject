package com.vini.core_data.repository

import com.vini.core_data.model.ApiResult
import com.vini.core_network.retrofit.LeaderboardApi
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LeaderboardsRepository @Inject constructor(
    private val api: LeaderboardApi
) {
    suspend fun getLeaderboards() = flow {
        try {
            val data = api.getLeaderboards()
            if (data.playersData.isNotEmpty()) {
                emit(ApiResult.Success(data.playersData))
            } else {
                emit(ApiResult.Error("Leaderboards error"))
            }
        } catch (exception: HttpException) {
            emit(ApiResult.Error("Leaderboards error: HttpException: ${exception.code()}"))
        } catch (exception: IOException) {
            emit(ApiResult.Error("Leaderboards error: IOException: ${exception.message?:""}"))
        } catch (exception: Exception) {
            emit(ApiResult.Error("Leaderboards error: Exception: ${exception.message?:""}"))
        }
    }
}