package com.vini.feature_news

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vini.core_model.model.news.Entire
import com.vini.core_ui.components.LorLoadingWheel
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NewsContent(viewModel: NewsViewModel = hiltViewModel()) {
    val metaDecksUIState by viewModel.state.collectAsState()
    AnimatedContent(targetState = metaDecksUIState,
        transitionSpec = {
            fadeIn() + slideInVertically(animationSpec = tween(400),
                initialOffsetY = { fullHeight -> fullHeight }) with
                    fadeOut(animationSpec = tween(200))
        }) { targetState ->
        when (targetState) {
            is NewsState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LorLoadingWheel(modifier = Modifier.background(Color.Transparent))
                }
            }
            is NewsState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = targetState.error,
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
            is NewsState.NewsList -> {
                NewsList(news = targetState.news)
            }
            else -> {
//            LeaderboardsEmpty()
            }
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
