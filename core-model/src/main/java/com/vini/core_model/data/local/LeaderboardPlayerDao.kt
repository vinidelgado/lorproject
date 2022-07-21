package com.vini.core_model.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vini.core_model.TABLE_NAME

@Dao
interface LeaderboardPlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlayers(playerList: List<LorLeaderboardPlayer>)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<LorLeaderboardPlayer>

}