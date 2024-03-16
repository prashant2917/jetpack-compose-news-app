package com.pocket.newsapp.pageintiationtopheadlines.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pocket.newsapp.network.NetworkService
import com.pocket.newsapp.topheadlines.data.entity.ApiArticle
import com.pocket.newsapp.utils.AppConstants.PAGE_SIZE

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PaginationTopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlinesArticles(): Flow<PagingData<ApiArticle>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { TopHeadlinePagingSource(networkService)
            }).flow
    }
}