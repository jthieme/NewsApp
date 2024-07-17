package com.thieme.newsapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thieme.newsapp.BottomMenuScreen
import com.thieme.newsapp.MockData
import com.thieme.newsapp.ui.screen.Categories
import com.thieme.newsapp.ui.screen.DetailScreen
import com.thieme.newsapp.ui.screen.Sources
import com.thieme.newsapp.ui.screen.TopNews
import com.thieme.newsapp.components.BottomMenu

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController = navController, scrollState = scrollState)
}

@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {
    Scaffold(bottomBar = {
        BottomMenu(navController)
    }) {
        Navigation(navController = navController, scrollState = scrollState)
    }

}

@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState) {
    NavHost(navController = navController, startDestination = "TopNews") {
        bottomNavigation(navController = navController)
        composable("TopNews") {
            TopNews(navController = navController)
        }
        composable("DetailScreen/{newsId}", arguments = listOf(navArgument("newsId"){type = NavType.IntType})) {
            navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData = MockData.getNewsById(id)
            DetailScreen(newsData, scrollState, navController)
        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController)
    }
    composable(BottomMenuScreen.Categories.route) {
        Categories()
    }
    composable(BottomMenuScreen.Sources.route) {
        Sources()
    }
}