package com.vini.feature_meta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.core_data.domain.LeaderboardsUseCase
import com.vini.core_data.domain.MetaDecksUseCase
import com.vini.core_data.model.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetaViewModel @Inject constructor(
    private val metaDecksUseCase: MetaDecksUseCase,
) : ViewModel() {
    val state = MutableStateFlow<MetaState>(MetaState.Loading)

    init {
        getMetaDecks()
    }

    fun getMetaDecks() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val result = metaDecksUseCase.invoke()
            viewModelScope.launch(context = Dispatchers.Main) {
                state.value = when (result) {
                    is ApiResult.Success -> {
                        if (result.data.isNullOrEmpty()) {
                            MetaState.Empty
                        } else {
                            MetaState.MetaDecks(deckList = result.data ?: emptyList())
                        }
                    }
                    is ApiResult.Error -> {
                        MetaState.Error(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is ApiResult.Loading -> {
                        MetaState.Loading
                    }
                }
            }
        }
    }
}
