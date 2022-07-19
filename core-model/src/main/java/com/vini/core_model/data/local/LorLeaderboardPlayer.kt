package com.vini.core_model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lorproject.leaderboards")
data class LorLeaderboardPlayer(
    var name: String,
    var rank: Int,
    var lp: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)