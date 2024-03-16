package com.pocket.newsapp.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pocket.newsapp.countrylist.presentation.CountryListRoute
import com.pocket.newsapp.home.presentation.HomeScreenRoute
import com.pocket.newsapp.languagelist.presentation.LanguageListRoute
import com.pocket.newsapp.newslist.presentation.NewsListRoute
import com.pocket.newsapp.newssource.presentation.NewsSourcesRoute
import com.pocket.newsapp.offline.OfflineTopHeadlineRoute
import com.pocket.newsapp.pageintiationtopheadlines.presentation.PaginationTopHeadlineRoute
import com.pocket.newsapp.search.presentation.SearchScreenRoute
import com.pocket.newsapp.topheadlines.presentation.TopHeadlineRoute
import com.pocket.newsapp.utils.AppConstants
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
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        composable(Route.OfflineTopHeadline.name) {
            OfflineTopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            }

            )
        }

        composable(Route.PaginationTopHeadline.name) {
            PaginationTopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            }

            )
        }

        composable(route = Route.NewsSources.name) {
            NewsSourcesRoute(onNewsClick = {
                navController.navigate(route = Route.NewsList.passData(sourceId = it))
            })
        }

        composable(route = Route.CountryList.name) {
            CountryListRoute(onCountryClick = {
                navController.navigate(route = Route.NewsList.passData(countryId = it))
            })
        }

        composable(route = Route.LanguageList.name) {
            LanguageListRoute(onLanguageClick = {
                navController.navigate(route = Route.NewsList.passData(languageId = it))
            })
        }

        composable(route = Route.Search.name) {
            SearchScreenRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        composable(route = Route.NewsList.name,
            arguments = listOf(
                navArgument(AppConstants.SOURCE_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(AppConstants.COUNTRY_ID) {
                    type = NavType.StringType
                    defaultValue = ""

                },
                navArgument(AppConstants.LANGUAGE_ID) {
                    type = NavType.StringType
                    defaultValue = ""

                }
            )
        ) { it ->
            val sourceId = it.arguments?.getString(AppConstants.SOURCE_ID).toString()
            val countryId = it.arguments?.getString(AppConstants.COUNTRY_ID).toString()
            val languageId = it.arguments?.getString(AppConstants.LANGUAGE_ID).toString()

            NewsListRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            }, sourceId = sourceId, countryId = countryId, languageId = languageId)
        }
    }
}