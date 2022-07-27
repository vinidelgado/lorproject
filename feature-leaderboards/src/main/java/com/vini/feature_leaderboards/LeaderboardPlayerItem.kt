package com.vini.feature_leaderboards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vini.core_model.model.leaderboards.PlayerData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardPlayerItem(player: PlayerData) {
    Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x0FFFFFFF)),
    ) {
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                rememberVectorPainter(image = Icons.Rounded.AccountCircle),
                contentDescription = "Player: ${player.name}",
                colorFilter = ColorFilter.tint(Color(0xFFF2F0F0)),
                modifier = Modifier.height(48.dp),
                contentScale = ContentScale.FillHeight
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    player.name,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFFF4F2F3),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "${player.lp} PDL",
                    color = Color(0xFFD4914E),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}