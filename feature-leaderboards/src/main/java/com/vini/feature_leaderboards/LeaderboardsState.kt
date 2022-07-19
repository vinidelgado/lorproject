package com.vini.feature_leaderboards

import com.vini.core_model.PlayerData

data class LeaderboardsState(
    val isLoading: Boolean = false,
    val players: List<PlayerData> = emptyList(),
    val error: String? = null
)