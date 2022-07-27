package com.vini.feature_meta

import com.vini.core_model.model.meta_decks.MetaData

sealed interface MetaState {
    object Loading : MetaState

    data class MetaDecks(
        val deckList: List<MetaData>,
    ) : MetaState

    object Empty : MetaState

    data class Error(
        val error: String,
    ) : MetaState
}