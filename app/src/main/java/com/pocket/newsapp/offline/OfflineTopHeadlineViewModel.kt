package com.pocket.newsapp.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.newsapp.base.ui.state.UiState
import com.pocket.newsapp.offline.data.OfflineTopHeadlineRepository
import com.pocket.newsapp.topheadlines.data.entity.Article
import com.pocket.newsapp.topheadlines.data.entity.toArticleEntity
import com.pocket.newsapp.utils.AppConstants
import com.pocket.newsapp.utils.DispatcherProvider
import com.pocket.newsapp.utils.NetworkHelper
import com.pocket.newsapp.utils.logger.Logger

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineTopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: OfflineTopHeadlineRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
    private val logger: Logger
) : ViewModel() {

    private val _topHeadlineUiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val topHeadlineUiState: StateFlow<UiState<List<Article>>> = _topHeadlineUiState

    private fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    init {
        startFetchingArticles()
    }

    fun startFetchingArticles() {
        if (checkInternetConnection()) {
            fetchArticles()
        } else {
            fetchArticlesFromDB()
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch(dispatcherProvider.main) {
            topHeadlineRepository.getTopHeadlinesArticles(AppConstants.COUNTRY)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _topHeadlineUiState.value = UiState.Error(e.toString())
                }.map {
                    it.map { apiArticle -> apiArticle.toArticleEntity(AppConstants.COUNTRY) }
                }.flatMapConcat {
                    flow {
                        emit(
                            topHeadlineRepository.deleteAndInsertAllTopHeadlinesArticles(
                                it,
                                AppConstants.COUNTRY
                            )
                        )
                    }
                }.flowOn(dispatcherProvider.io).catch { e ->
                    _topHeadlineUiState.value = UiState.Error(e.toString())
                }.collect {
                    fetchArticlesFromDB()
                }
        }
    }

    private fun fetchArticlesFromDB() {
        viewModelScope.launch(dispatcherProvider.main) {
            topHeadlineRepository.getTopHeadlinesArticlesFromDB(AppConstants.COUNTRY)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _topHeadlineUiState.value = UiState.Error(e.toString())
                }
                .collect {
                    if (!checkInternetConnection() && it.isEmpty()) {
                        _topHeadlineUiState.value = UiState.Error("Data Not found.")
                    } else {
                        _topHeadlineUiState.value = UiState.Success(it)
                        logger.debug("OfflineTopHeadlineViewModel", "Success")
                    }
                }
        }
    }
}