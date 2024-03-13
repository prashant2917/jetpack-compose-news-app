package com.pocket.newsapp.offline.data


import com.pocket.newsapp.local.data.DatabaseService
import com.pocket.newsapp.network.NetworkService
import com.pocket.newsapp.topheadlines.data.entity.ApiArticle
import com.pocket.newsapp.topheadlines.data.entity.Article
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class OfflineTopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun getTopHeadlinesArticles(countryID: String): Flow<List<ApiArticle>> {
        return flow { emit(networkService.getTopHeadlines(countryID)) }
            .map {
                it.apiArticles
            }
    }

    fun deleteAndInsertAllTopHeadlinesArticles(articles: List<Article>, country: String) {
        databaseService.deleteAndInsertAllTopHeadlinesArticles(articles, country)
    }

    fun getTopHeadlinesArticlesFromDB(countryID: String): Flow<List<Article>> {
        return databaseService.getAllTopHeadlinesArticles(countryID)
    }
}