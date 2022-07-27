package com.vini.feature_leaderboards

import com.vini.core_model.model.leaderboards.PlayerData

//data class LeaderboardsState(
//    val isLoading: Boolean = false,
//    val players: List<PlayerData> = emptyList(),
//    val error: String? = null
//)

sealed interface LeaderboardsState {
    object Loading : LeaderboardsState

    data class Leaderboard(
        val players: List<PlayerData>,
    ) : LeaderboardsState

    object Empty : LeaderboardsState

    data class Error(
        val error: String,
    ) : LeaderboardsState
}