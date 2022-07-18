package com.vini.core_data.repository

import com.vini.core_data.model.Resource
import com.vini.core_network.retrofit.LeaderboardApi
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LeaderboardsRepository @Inject constructor(
    private val api: LeaderboardApi
) {
    suspend fun getLeaderboards() = flow {
        emit(Resource.Loading(true))
        try {
            val data = api.getLeaderboards()
            if (data.playersData.isNotEmpty()) {
                emit(Resource.Success(data.playersData))
            } else {
                emit(Resource.Error("Leaderboards error"))
            }
        } catch (exception: HttpException) {
            emit(Resource.Error("Leaderboards error: HttpException: ${exception.code()}"))
        } catch (exception: IOException) {
            emit(Resource.Error("Leaderboards error: IOException: ${exception.message?:""}"))
        } catch (exception: Exception) {
            emit(Resource.Error("Leaderboards error: Exception: ${exception.message?:""}"))
        }
        emit(Resource.Loading(false))
    }
}