package com.vini.core_data.repository

import com.vini.core_model.model.news.Entire
import com.vini.core_network.retrofit.LeaderboardApi
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: LeaderboardApi,
) {
    suspend fun getNews(): List<Entire> = api.getNews().newsList
}

