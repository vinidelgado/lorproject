package com.vini.core_model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vini.core_model.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class LorLeaderboardPlayer(
    var name: String,
    var rank: Int,
    var lp: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)