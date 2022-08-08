package com.vini.feature_leaderboards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vini.core_model.model.leaderboards.PlayerData
import com.vini.core_ui.components.AnimatedShimmer
import com.vini.core_ui.components.LorTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalLayoutApi
@Composable
fun LeaderboardsScreen(
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color(0xFF141114),
            darkIcons = false
        )
    }
    Scaffold(
        topBar = {
            LorTopAppBar(
                titleRes = R.string.leaderboard_feature_title,
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                )
            )
        },
        containerColor = Color(0xFF141214),
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                LeaderboardsContent()
            }
        }
    }
}


@Composable
fun LeaderboardsContent(viewModel: LeaderboardsViewModel = hiltViewModel()) {
    val leaderboardsUIState by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        var text by remember { mutableStateOf("") }
        LeaderboardSearchPlayer(
            searchText = text,
            labelText = "Player name",
            onSearchTextChanged = {
                if (it.isNotEmpty() && it.length > 2) {
                    viewModel.filterLeaderboardStream(it)
                } else if (it.isEmpty()) {
                    viewModel.getLeaderboards()
                }
                text = it
            },
            onClearClick = {
                text = ""
                viewModel.getLeaderboards()
            })

        when (val uiState = leaderboardsUIState) {
            is LeaderboardsState.Loading -> {
                LeaderboardsLoading()
            }
            is LeaderboardsState.Error -> {
                LeaderboardsError(error = uiState.error)
            }
            is LeaderboardsState.Leaderboard -> {
                LeaderboardsSuccess(players = uiState.players)
            }
            else -> {
                LeaderboardsEmpty()
            }
        }

    }
}

@Composable
fun LeaderboardsLoading() {
    Column {
        repeat(8) {
            AnimatedShimmer()
        }
    }
}

@Composable
fun LeaderboardsError(error: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color(0xFFF2F0F0)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD13739))) {
            Text(
                text = "Tentar novamente",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W700,
            )
        }
    }
}

@Composable
fun LeaderboardsEmpty() {
    Text(
        text = "Don´t have players on master tier",
        color = Color.Red,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun LeaderboardsSuccess(
    players: List<PlayerData>,
) {
    val listState = rememberLazyListState()
    Spacer(modifier = Modifier.height(16.dp))
    players.let { playerList ->
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(all = 0.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(playerList.size) { index ->
                LeaderboardPlayerItem(player = playerList[index])
            }
        }
    }
}
