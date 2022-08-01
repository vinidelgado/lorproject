package com.vini.feature_meta

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vini.core_designsystem.R
import com.vini.core_model.model.meta_decks.MetaData


@Composable
fun MetaDeckItem(metaData: MetaData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x0FFFFFFF))
            .padding(top = 16.dp)
    ) {
        Text(
            text = metaData.archetype,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xFFF2F0F0)
        )
        Row(
            modifier = Modifier
                .height(120.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegionMetaDeckItem(metaData = metaData)
            Spacer(modifier = Modifier.width(8.dp))
            ChampionsMetaDeckItem(metaData = metaData)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MatchesMetaDeckItem(metaData = metaData)
                WinRateMetaDeckItem(metaData = metaData)
                PlayRateMetaDeckItem(metaData = metaData)
            }
        }
        ButtonCopyCode(metaData = metaData)
    }
}

@Composable
fun ButtonCopyCode(metaData: MetaData) {
    val clipboardManager = LocalClipboardManager.current
    Button(
        onClick = {
            clipboardManager.setText(AnnotatedString(metaData.bestDecks[0]))
        },
        shape = CutCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD13739)),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = "Copy Deck Code".toUpperCase(),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xFFF2F0F0)
        )
    }
}

@Composable
fun MatchesMetaDeckItem(metaData: MetaData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Matches",
            fontWeight = FontWeight.W700,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFF2F0F0)
        )
        Text(
            metaData.totalMatches.toString(),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFF2F0F0)
        )
    }
}

@Composable
fun WinRateMetaDeckItem(metaData: MetaData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Win Rate",
            fontWeight = FontWeight.W700,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFF2F0F0)
        )
        Text(
            text = "${metaData.winRate}%",
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFF2F0F0)
        )
    }
}

@Composable
fun PlayRateMetaDeckItem(metaData: MetaData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Play Rate",
            fontWeight = FontWeight.W700,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFF2F0F0)
        )
        Text(
            text = "${metaData.playRate}%",
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFF2F0F0)
        )
    }
}

@Composable
fun ChampionsMetaDeckItem(metaData: MetaData) {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        metaData.assets.champions.forEach {
            ChampionImageMetaDeckItem(championCode = it[1])
        }
    }
}

@Composable
fun RegionMetaDeckItem(metaData: MetaData) {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        metaData.assets.regions.forEach {
            Image(
                painter = painterResource(getDrawableRegion(it)),
                contentDescription = "content description",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun ChampionImageMetaDeckItem(championCode: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://runescola.com.br/wp-content/plugins/deck-viewer/assets/images/champions/webp/${championCode}.webp")
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_sh),
        contentDescription = "Nelson Jones",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .size(42.dp)
    )
}


private fun getDrawableRegion(regionCode: String): Int {
    return when (regionCode) {
        "BW" -> {
            R.drawable.ic_bw
        }
        "NX" -> {
            R.drawable.ic_nx
        }
        "MT" -> {
            R.drawable.ic_mt
        }
        "RU" -> {
            R.drawable.ic_ru
        }
        "IO" -> {
            R.drawable.ic_io
        }
        "PZ" -> {
            R.drawable.ic_pz
        }
        "SI" -> {
            R.drawable.ic_si
        }
        "SH" -> {
            R.drawable.ic_sh
        }
        "BC" -> {
            R.drawable.ic_bc
        }
        "FR" -> {
            R.drawable.ic_fr
        }
        "DE" -> {
            R.drawable.ic_de
        }
        else -> {
            Log.d("RegionNotFound", "Região não tem - $regionCode")
            R.drawable.ic_ru
        }
    }
}