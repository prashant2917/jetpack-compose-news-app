package com.pocket.newsapp.search.data


import com.pocket.newsapp.network.NetworkService
import com.pocket.newsapp.topheadlines.data.entity.Article
import com.pocket.newsapp.topheadlines.data.entity.toArticleEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class SearchRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsByQueries(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByQueries(query))
        }.map {
            it.apiArticles.map { apiArticle -> apiArticle.toArticleEntity(query) }
        }
    }
}