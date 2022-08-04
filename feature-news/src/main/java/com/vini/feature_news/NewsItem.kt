package com.vini.feature_news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vini.core_model.model.news.Entire


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItem(newsData: Entire) {
    Card(
        onClick = {
            //TODO: Click
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x0FFFFFFF)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                .data(newsData.fileName)
                    .crossfade(true)
                    .build(),
                contentDescription = "Nelson Jones",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = newsData.title,
                style = MaterialTheme.typography.headlineSmall,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 8.dp),
                textAlign = TextAlign.Start,
                color = Color(0xFFF2F0F0)
            )
            Text(
                text = newsData.summary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 8.dp),
                textAlign = TextAlign.Start,
                color = Color(0xCCF0EDEE)
            )

            Text(
                text = if (newsData.createdAt == "1") {
                    "${newsData.createdAt} DIA ATRÁS"
                } else {
                    "${newsData.createdAt} DIAS ATRÁS"
                },
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 16.dp),
                textAlign = TextAlign.Start,
                color = Color(0xCCF0EDEE)
            )
        }
    }
}