package com.vini.core_data.domain

import android.util.Log
import com.vini.core_data.model.ApiResult
import com.vini.core_data.repository.LeaderboardsRepository
import com.vini.core_data.repository.MetaRepository
import com.vini.core_data.repository.NewsRepository
import com.vini.core_model.model.leaderboards.PlayerData
import com.vini.core_model.data.local.LorLeaderboardPlayer
import com.vini.core_model.model.meta_decks.MetaData
import com.vini.core_model.model.news.Entire
import retrofit2.HttpException
import java.io.IOException
import java.lang.NullPointerException
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {
    suspend operator fun invoke(): ApiResult<List<Entire>> {
        return try {
            val news = repository.getNews()
            if(news.isNotEmpty()){
                ApiResult.Success(news)
            }else{
                ApiResult.Success(emptyList())
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
}