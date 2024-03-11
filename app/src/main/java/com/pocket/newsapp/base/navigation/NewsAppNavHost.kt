package com.pocket.newsapp.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pocket.newsapp.home.presentation.HomeScreenRoute
import com.pocket.newsapp.topheadlines.presentation.TopHeadlineRoute
import com.pocket.newsapp.utils.openCustomChromeTab

@Composable
fun NewsAppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Route.HomeScreen.name) {
        composable(Route.HomeScreen.name) {
            HomeScreenRoute(navController = navController)
        }
        
        composable(Route.TopHeadline.name) {
            TopHeadlineRoute(onNewsClick =  {
                openCustomChromeTab(context,  it)
            })
        }
    }
}