package com.vini.core_data.di

import com.vini.core_data.domain.LeaderboardsUseCase
import com.vini.core_data.domain.MetaDecksUseCase
import com.vini.core_data.repository.LeaderboardsRepository
import com.vini.core_data.repository.MetaRepository
import com.vini.core_model.data.local.LeaderboardConfigDao
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
    fun provideMetaDecksUseCase(
        repository: MetaRepository,
    ): MetaDecksUseCase =
        MetaDecksUseCase(repository)

    @Provides
    @Singleton
    fun provideLeaderboardsRepository(
        api: LeaderboardApi,
        leaderboardPlayerDao: LeaderboardPlayerDao,
        leaderboardConfigDao: LeaderboardConfigDao,
    ): LeaderboardsRepository =
        LeaderboardsRepository(
            api = api,
            leaderboardPlayerDao = leaderboardPlayerDao,
            leaderboardConfigDao = leaderboardConfigDao
        )


    @Provides
    @Singleton
    fun provideMetaRepository(
        api: LeaderboardApi,
    ): MetaRepository = MetaRepository(api = api)
}