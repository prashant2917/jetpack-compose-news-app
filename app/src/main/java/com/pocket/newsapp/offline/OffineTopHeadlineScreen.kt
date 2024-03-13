package com.pocket.newsapp.offline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
fun OfflineTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    topHeadlineViewModel: OfflineTopHeadlineViewModel = hiltViewModel()
) {

    val newsUiState: UiState<List<Article>> by topHeadlineViewModel.topHeadlineUiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(4.dp)) {
        TopHeadlineScreen(newsUiState, onNewsClick, onRetryClick = {
            topHeadlineViewModel.startFetchingArticles()
        })
    }
}

@Composable
fun TopHeadlineScreen(
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
    }
}

