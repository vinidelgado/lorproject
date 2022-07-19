package com.vini.core_model.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LorLeaderboardPlayer::class],
    version = 1
)
abstract class LorProjectDatabase : RoomDatabase() {
    abstract fun lorLeaderboardsDao(): LeaderboardPlayerDao
}