package com.thieme.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thieme.newsapp.MockData
import com.thieme.newsapp.MockData.getTimeAgo
import com.thieme.newsapp.NewsData

@Composable
fun TopNews(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(MockData.topNewsList) {
                newsData -> TopNewsItem(newsData = newsData, onNewsClick = {
                    navController.navigate("DetailScreen/${newsData.id}")
            })
            }
        }
    }
}

@Composable
fun TopNewsItem(newsData: NewsData, onNewsClick: () -> Unit = {}) {
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp).clickable {
            onNewsClick()
        }) {
        Image(painter = painterResource(id = newsData.image), contentDescription = "", contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = MockData.stringToDate(newsData.publishedAt).getTimeAgo(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = newsData.title, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopNews() {
//    TopNews(rememberNavController())
    TopNewsItem(
        newsData = NewsData(
            2,
            author = "Namita Singh",
            title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
            description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
            publishedAt = "2021-11-04T04:42:40Z"
        )
    )
}