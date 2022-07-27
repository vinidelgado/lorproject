package com.vini.core_model.model.meta_decks

import com.squareup.moshi.Json

data class AssetData(
    @field:Json(name = "regions")
    val regions: List<String>,
    @field:Json(name = "champions")
    val champions: List<List<String>>,
)