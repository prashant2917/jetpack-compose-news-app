package com.pocket.newsapp.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.pocket.newsapp.R
import com.pocket.newsapp.base.navigation.Route
import com.pocket.newsapp.base.ui.presentation.TitleButton

@Composable
fun HomeScreenRoute(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreen(navController)
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleButton(
            title = stringResource(id = R.string.top_headlines),
            navController = navController,
            routeName = Route.TopHeadline.name
        )

        TitleButton(
            stringResource(R.string.offline_top_headlines),
            navController,
            Route.OfflineTopHeadline.name
        )

        TitleButton(
            stringResource(R.string.pagination_top_headlines),
            navController,
            Route.PaginationTopHeadline.name
        )

        TitleButton(stringResource(R.string.news_sources), navController, Route.NewsSources.name)

        TitleButton(stringResource(R.string.countries), navController, Route.CountryList.name)

        TitleButton(stringResource(R.string.language), navController, Route.LanguageList.name)

        TitleButton(stringResource(R.string.search), navController, Route.Search.name)
    }
}