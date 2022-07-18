package com.vini.core_data.di

import com.vini.core_data.repository.LeaderboardsRepository
import com.vini.core_network.retrofit.LeaderboardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@ExperimentalCoroutinesApi
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class DataModule {
//    @Binds
//    @Singleton
//    abstract fun bindLeaderboardsRepository(
//        leaderboardsRepositoryImpl: LeaderboardsRepository
//    ): LeaderboardsRepository
//}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideLeaderboardsRepository(api:LeaderboardApi): LeaderboardsRepository = LeaderboardsRepository(api)
}