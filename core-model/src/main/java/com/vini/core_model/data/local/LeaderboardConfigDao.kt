package com.vini.core_model.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.core_model.TABLE_NAME_CONFIG

@Dao
interface LeaderboardConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(lorLeaderboardConfig: LorLeaderboardConfig)

    @Query("DELETE FROM $TABLE_NAME_CONFIG")
    fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME_CONFIG")
    fun getAll(): LorLeaderboardConfig



}