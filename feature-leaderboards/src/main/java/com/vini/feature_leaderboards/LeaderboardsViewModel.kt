package com.vini.feature_leaderboards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.core_data.domain.LeaderboardsUseCase
import com.vini.core_data.model.ApiResult
import com.vini.core_data.repository.LeaderboardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardsViewModel @Inject constructor(
    private val leaderboardsUseCase: LeaderboardsUseCase,
) : ViewModel() {
    var state by mutableStateOf(LeaderboardsState())
    var filtredState by mutableStateOf(LeaderboardsState())

    init {
        getLeaderboards()
    }

    private fun getLeaderboards() {
        leaderboardsUseCase().onEach { result ->
            state = when (result) {
                is ApiResult.Success -> {
                    LeaderboardsState(players = result.data ?: emptyList())
                }
                is ApiResult.Error -> {
                    LeaderboardsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is ApiResult.Loading -> {
                    LeaderboardsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun filterLeaderboardStream(namePlayer: String) {
        viewModelScope.launch {
            if (filtredState.players.isNotEmpty()) {
                val filtredPlayers = state.players.filter {
                    it.name.contains(namePlayer, ignoreCase = true)
                }
                if (filtredPlayers.isNotEmpty()) {
                    filtredState = state.copy(
                        players = filtredPlayers
                    )
                }
            }
        }
    }
}
