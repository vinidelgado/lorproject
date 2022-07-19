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
        leaderboardPlayerDao: LeaderboardPlayerDao,
        repository: LeaderboardsRepository
    ): LeaderboardsUseCase =
        LeaderboardsUseCase(leaderboardPlayerDao, repository)
//        LeaderboardsUseCase(repository)

    @Provides
    @Singleton
    fun provideLeaderboardsRepository(api: LeaderboardApi): LeaderboardsRepository =
        LeaderboardsRepository(api)
}