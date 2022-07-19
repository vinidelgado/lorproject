package com.vini.core_model.data.local

import androidx.room.*

@Dao
interface LeaderboardPlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayers(shoppingItem: LorLeaderboardPlayer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlayers(playerList:List<LorLeaderboardPlayer>)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: LorLeaderboardPlayer)

}