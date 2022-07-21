package com.vini.feature_leaderboards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.core_data.domain.LeaderboardsUseCase
import com.vini.core_data.model.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    fun getLeaderboards() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val result = leaderboardsUseCase.invoke()
            viewModelScope.launch(context = Dispatchers.Main) {
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
            }
        }
    }

    fun filterLeaderboardStream(namePlayer: String) {
        if (namePlayer.trim().length > 2) {
            viewModelScope.launch(context = Dispatchers.IO) {
                val result = leaderboardsUseCase.invoke()
                val filtredPlayers = result.data?.filter {
                    it.name.contains(namePlayer, ignoreCase = true)
                } ?: emptyList()
                if (filtredPlayers.isNotEmpty()) {
                    viewModelScope.launch(context = Dispatchers.Main) {
                        state = state.copy(
                            players = filtredPlayers
                        )
                    }
                }
            }
        }
    }
}
