package com.vini.core_model.model.meta_decks

import com.squareup.moshi.Json
import com.vini.core_model.model.leaderboards.PlayerData

data class MetaDecks(
    @field:Json(name = "meta")
    val deckList: List<MetaData>
)