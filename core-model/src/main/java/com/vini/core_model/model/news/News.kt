package com.vini.core_model.model.news

import com.squareup.moshi.Json

data class News(
    @field:Json(name = "entries")
    val newsList: List<Entire>
)