package com.vini.feature_news

import com.vini.core_model.model.news.Entire

sealed interface NewsState {
    object Loading : NewsState

    data class NewsList(
        val news: List<Entire>,
    ) : NewsState

    object Empty : NewsState

    data class Error(
        val error: String,
    ) : NewsState
}