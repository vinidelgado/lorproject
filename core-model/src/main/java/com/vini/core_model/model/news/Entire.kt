package com.vini.core_model.model.news

import com.squareup.moshi.Json

data class Entire(
    @field:Json(name = "filename")
    val fileName: String,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "date")
    val date: String,
    @field:Json(name = "summary")
    val summary: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "url")
    val url: String,
)