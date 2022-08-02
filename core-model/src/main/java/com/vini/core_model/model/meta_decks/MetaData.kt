package com.vini.core_model.model.meta_decks

import com.squareup.moshi.Json

data class MetaData(
    @field:Json(name = "archetype")
    val archetype: String,
    @field:Json(name = "assets")
    val assets: AssetData,
    @field:Json(name = "total_matches")
    val totalMatches: Int,
    @field:Json(name = "playrate")
    val playRate: Float,
    @field:Json(name = "winrate")
    val winRate: Float,
    @field:Json(name = "best_decks")
    val bestDecks: List<String>,
)