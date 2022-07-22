package com.vini.feature_leaderboards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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
    val listState = rememberLazyListState()
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

        Spacer(modifier = Modifier.height(16.dp))
        viewModel.state.players.let { playerList ->
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

        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
            )
        }
    }
}
