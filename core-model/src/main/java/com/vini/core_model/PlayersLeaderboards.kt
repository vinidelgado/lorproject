package com.vini.core_model

import com.squareup.moshi.Json

data class PlayersLeaderboards(
    @field:Json(name = "players")
    val playersData: List<PlayerData>
)