package com.vini.feature_leaderboards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vini.core_model.PlayerData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalLayoutApi
@Composable
fun LeaderboardsScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
//            LeaderboardsTitle()
        },
        containerColor = Color(0xFF141214),
        modifier = modifier.fillMaxSize()
    ){ innerPadding ->
        Text("Teste",modifier = modifier
            .padding(innerPadding)
            .consumedWindowInsets(innerPadding))
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
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier
        .padding(vertical = 24.dp)) {

//        if (viewModel.state.isLoading) {
//            CircularProgressIndicator()
//        }

        viewModel.state.playersData.let { playerList ->
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(all = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(playerList.size) { index ->
                    LeaderboardPlayerItem(player = playerList[index])
                }
            }
        }

        viewModel.state.selected.let {
            if (it != -1) {
                coroutineScope.launch {
                    listState.animateScrollToItem(index = it)
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
