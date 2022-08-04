package com.vini.core_ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vini.core_ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LorLoadingWheel(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading))
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF))) {
        LottieAnimation(
            modifier = Modifier.size(72.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}