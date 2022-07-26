package com.vini.feature_leaderboards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.core_data.domain.LeaderboardsUseCase
import com.vini.core_data.model.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardsViewModel @Inject constructor(
    private val leaderboardsUseCase: LeaderboardsUseCase,
) : ViewModel() {
    val state = MutableStateFlow<LeaderboardsState>(LeaderboardsState.Loading)

    init {
        getLeaderboards()
    }

    fun getLeaderboards() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val result = leaderboardsUseCase.invoke()
            viewModelScope.launch(context = Dispatchers.Main) {
                state.value = when (result) {
                    is ApiResult.Success -> {
                        if (result.data.isNullOrEmpty()) {
                            LeaderboardsState.Empty
                        } else {
                            LeaderboardsState.Leaderboard(players = result.data ?: emptyList())
                        }
                    }
                    is ApiResult.Error -> {
                        LeaderboardsState.Error(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is ApiResult.Loading -> {
                        LeaderboardsState.Loading
                    }
                }
            }
        }
    }

    fun filterLeaderboardStream(namePlayer: String) {
        if (namePlayer.trim().length > 2) {
            state.value = LeaderboardsState.Loading
            viewModelScope.launch(context = Dispatchers.IO) {
                val result = leaderboardsUseCase.invoke()
                val filtredPlayers = result.data?.filter {
                    it.name.contains(namePlayer, ignoreCase = true)
                } ?: emptyList()
                if (filtredPlayers.isNotEmpty()) {
                    viewModelScope.launch(context = Dispatchers.Main) {
                        state.value = LeaderboardsState.Leaderboard(players = filtredPlayers)
                    }
                }
            }
        }
    }
}
