package com.vini.core_model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vini.core_model.TABLE_NAME_CONFIG

@Entity(tableName = TABLE_NAME_CONFIG)
data class LorLeaderboardConfig(
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,
    var lastUpdate: String
)