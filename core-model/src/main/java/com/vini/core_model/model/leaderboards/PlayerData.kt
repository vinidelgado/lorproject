package com.vini.core_model.model.leaderboards

import com.squareup.moshi.Json

data class PlayerData(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "rank")
    val rank: Int,
    @field:Json(name = "lp")
    val lp: Int,
)