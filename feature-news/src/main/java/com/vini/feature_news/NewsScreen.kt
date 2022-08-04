package com.vini.feature_news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vini.core_model.model.news.Entire
import com.vini.core_ui.components.LorTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalLayoutApi
@Composable
fun NewsScreen(
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
                titleRes = R.string.news_feature_title,
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
                modifier = modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                NewsContent()
            }
        }
    }
}

@Composable
fun NewsContent(viewModel: NewsViewModel = hiltViewModel()) {
    val metaDecksUIState by viewModel.state.collectAsState()

    when (val uiState = metaDecksUIState) {
        is NewsState.Loading -> {
//            LeaderboardsLoading()
        }
        is NewsState.Error -> {
//            LeaderboardsError(error = uiState.error)
        }
        is NewsState.NewsList -> {
            NewsList(news = uiState.news)
        }
        else -> {
//            LeaderboardsEmpty()
        }
    }
}


@Composable
fun NewsList(news: List<Entire>) {
    LazyColumn(
        contentPadding = PaddingValues(all = 0.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(news.size) { index ->
            NewsItem(newsData = news[index])
        }
    }
}