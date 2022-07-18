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
        containerColor = Color.Transparent,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardPlayers(player: PlayerData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 0.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2E2651)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = player.rank.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp),
                color = Color(0xFFAAABCA),
                fontWeight = FontWeight.Bold,
//                style = typography.body1,
                textAlign = TextAlign.Center,
            )
            Text(
                player.name,
                modifier = Modifier
                    .weight(3f),
                color = Color(0xFFFFFFFF),
                fontWeight = FontWeight.Medium,
//                style = typography.body2,
                textAlign = TextAlign.Start,
            )
            Text(
                player.lp.toString(),
                modifier = Modifier
                    .weight(1f),
                color = Color(0xFF8890B5),
                fontWeight = FontWeight.Bold,
//                style = typography.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun LeaderboardsContent(viewModel: LeaderboardsViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.background(Color(0xFF19133D)).padding(vertical = 24.dp)) {

//        if (viewModel.state.isLoading) {
//            CircularProgressIndicator()
//        }

        viewModel.state.playersData.let { playerList ->
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(all = 4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(playerList.size) { index ->
                    LeaderboardPlayers(playerList[index])
//                    Text(playerList[index].name, color = Color.White)
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
