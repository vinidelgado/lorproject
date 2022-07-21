package com.vini.core_data.di

import com.vini.core_data.domain.LeaderboardsUseCase
import com.vini.core_data.repository.LeaderboardsRepository
import com.vini.core_model.data.local.LeaderboardPlayerDao
import com.vini.core_network.retrofit.LeaderboardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideLeaderboardsUseCase(
        repository: LeaderboardsRepository,
    ): LeaderboardsUseCase =
        LeaderboardsUseCase(repository)

    @Provides
    @Singleton
    fun provideLeaderboardsRepository(
        api: LeaderboardApi,
        leaderboardPlayerDao: LeaderboardPlayerDao
    ): LeaderboardsRepository =
        LeaderboardsRepository(api = api,leaderboardPlayerDao = leaderboardPlayerDao)
}