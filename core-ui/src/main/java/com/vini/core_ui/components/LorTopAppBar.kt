package com.vini.core_ui.components

import androidx.annotation.StringRes
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LorTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes),
                fontWeight = FontWeight.W700,
                color = Color(0xFFFFFFFF),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        elevation = 0.dp
    )
}