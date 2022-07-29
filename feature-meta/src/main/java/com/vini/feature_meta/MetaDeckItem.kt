package com.vini.feature_meta

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
            .background(Color.White)
    ) {
        Text(metaData.archetype)
        Row(verticalAlignment = Alignment.CenterVertically) {
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
    }
}

@Composable
fun MatchesMetaDeckItem(metaData: MetaData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Matches")
        Text(metaData.totalMatches.toString())
    }
}

@Composable
fun WinRateMetaDeckItem(metaData: MetaData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Win Rate")
        Text(metaData.winRate.toString())
    }
}

@Composable
fun PlayRateMetaDeckItem(metaData: MetaData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Play Rate")
        Text(metaData.playRate.toString())
    }
}

@Composable
fun ChampionsMetaDeckItem(metaData: MetaData) {
    Column() {
        metaData.assets.champions.forEach{
            ChampionImageMetaDeckItem(championCode = it[1])
        }
    }
}

@Composable
fun RegionMetaDeckItem(metaData: MetaData) {
    Column() {
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
            .size(48.dp)
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