package com.vini.core_model.di

import android.content.Context
import androidx.room.Room
import com.vini.core_model.DATABASE_NAME
import com.vini.core_model.data.local.LorProjectDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModelModule {
    @Singleton
    @Provides
    fun provideLorProjectDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, LorProjectDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideLorLeaderboardsDao(
        database: LorProjectDatabase
    ) = database.lorLeaderboardsDao()



}