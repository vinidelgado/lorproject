package com.vini.feature_leaderboards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.core_data.model.Resource
import com.vini.core_data.repository.LeaderboardsRepository
import com.vini.core_model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(InternalCoroutinesApi::class)
class LeaderboardsViewModel @Inject constructor(
    private val leaderRepository: LeaderboardsRepository,
) : ViewModel() {

    var state by mutableStateOf(LorAmericasLeaderboards())
        private set

    fun loadLeaderboardStream() {
        viewModelScope.launch {
            leaderRepository.getLeaderboards().collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let { data ->
                            state = state.copy(
                                playersData = data,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}

data class LorAmericasLeaderboards(
    val playersData: List<PlayerData> = ArrayList(),
    val selected: Int = -1,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface LeaderboardsUiState {
    object Loading : LeaderboardsUiState

    data class Interests(
        val authors: List<LeaderboardsUiState>
    ) : LeaderboardsUiState

    object Empty : LeaderboardsUiState
}