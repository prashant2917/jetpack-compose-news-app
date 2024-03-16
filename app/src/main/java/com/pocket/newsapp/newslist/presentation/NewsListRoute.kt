package com.pocket.newsapp.newslist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pocket.newsapp.base.ui.presentation.ArticleList
import com.pocket.newsapp.base.ui.presentation.ShowError
import com.pocket.newsapp.base.ui.presentation.ShowLoading
import com.pocket.newsapp.base.ui.state.UiState
import com.pocket.newsapp.topheadlines.data.entity.Article


@Composable
fun NewsListRoute(
    onNewsClick: (url: String) -> Unit,
    newsViewModel: NewsListViewModel = hiltViewModel(),
    sourceId: String? = null,
    countryId: String? = null,
    languageId: String? = null
) {

    val newsUiState: UiState<List<Article>> by newsViewModel.newUiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {

        if (!countryId.isNullOrEmpty()) {
            newsViewModel.fetchNewsByCountry(countryId)
        } else if (!languageId.isNullOrEmpty()) {
            newsViewModel.fetchNewsByLanguage(languageId)
        } else if (!sourceId.isNullOrEmpty()) {
            newsViewModel.fetchNewsBySources(sourceId)
        }
    })


    Column(modifier = Modifier.padding(4.dp)) {
        NewsListScreen(newsUiState, onNewsClick, onRetryClick = {
            if (!countryId.isNullOrEmpty()) {
                newsViewModel.fetchNewsByCountry(countryId)
            } else if (!languageId.isNullOrEmpty()) {
                newsViewModel.fetchNewsByLanguage(languageId)
            } else if (!sourceId.isNullOrEmpty()) {
                newsViewModel.fetchNewsBySources(sourceId)
            }
        })
    }
}

@Composable
fun NewsListScreen(
    uiState: UiState<List<Article>>,
    onNewsClick: (url: String) -> Unit,
    onRetryClick: () -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(text = uiState.message) {
                onRetryClick()
            }
        }

        else -> {}
    }
}