package com.vini.core_data.di

import com.vini.core_data.repository.LeaderboardsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindLeaderboardsRepository(
        leaderboardsRepositoryImpl: LeaderboardsRepository
    ): LeaderboardsRepository
}