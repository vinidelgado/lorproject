package com.vini.feature_leaderboards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.core_data.model.ApiResult
import com.vini.core_data.repository.LeaderboardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardsViewModel @Inject constructor(
    private val leaderRepository: LeaderboardsRepository,
) : ViewModel() {
    var state by mutableStateOf(LeaderboardsState())

    init {
        loadLeaderboardStream()
    }

    fun filterLeaderboardStream(namePlayer:String){
        viewModelScope.launch {
            if(state.players.isNotEmpty()){
                val filtredPlayers = state.players.filter {
                    it.name.contains(namePlayer, ignoreCase = true)
                }
                if(filtredPlayers.isNotEmpty()){
                    state = state.copy(
                        players = filtredPlayers
                    )
                }
            }
        }
    }

    fun loadLeaderboardStream() {
        viewModelScope.launch {
            leaderRepository.getLeaderboards().collectLatest { result ->
                when (result) {
                    is ApiResult.Success -> {
                        result.data?.let { data ->
                            state = state.copy(
                                players = data,
                            )
                        }
                    }
                    is ApiResult.Error -> {
                        state = state.copy(
                            error = result.message,
                        )
                    }
                    is ApiResult.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}
