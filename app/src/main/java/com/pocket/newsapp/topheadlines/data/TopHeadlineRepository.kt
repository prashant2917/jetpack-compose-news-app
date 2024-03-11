package com.pocket.newsapp.topheadlines.data

import com.pocket.newsapp.network.NetworkService
import com.pocket.newsapp.topheadlines.data.entity.ApiArticle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {
    fun getTopHeadlinesArticles(countryID: String): Flow<List<ApiArticle>> {
        return flow { emit(networkService.getTopHeadlines(countryID)) }
            .map {
                it.apiArticles
            }
    }
}