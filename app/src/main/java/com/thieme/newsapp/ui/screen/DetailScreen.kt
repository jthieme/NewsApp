package com.thieme.newsapp.ui.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thieme.newsapp.MockData
import com.thieme.newsapp.MockData.getTimeAgo
import com.thieme.newsapp.NewsData
import com.thieme.newsapp.R

@Composable
fun DetailScreen(newsData: NewsData, scrollState: ScrollState, navController: NavController) {
    Scaffold(topBar = {
        DetailTopAppBar(onBackPressed = {navController.popBackStack()})
    }) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)
            Image(painter = painterResource(id = newsData.image), contentDescription = "")
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                InfoWithIcon(icon = Icons.Default.Edit, info = newsData.author)
                InfoWithIcon(icon = Icons.Default.DateRange, info = MockData.stringToDate(newsData.publishedAt).getTimeAgo())
            }
            Text(text = newsData.title, fontWeight = FontWeight.Bold)
            Text(text = newsData.description, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun DetailTopAppBar(onBackPressed: () -> Unit = {}) {
    TopAppBar(title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")

            }
        }
    )
}

@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(icon, contentDescription = "Author", modifier = Modifier.padding(end = 8.dp),
        colorResource(id = R.color.purple_500))
        Text(text = info)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        NewsData(
            2,
            author = "Josh Thieme",
            title = "Come and See",
            description = "Come and See is a cool thing that does stuff",
            publishedAt = "2024-09-23T20:18:00Z"
        ),
        rememberScrollState(),
        rememberNavController()
    )
}