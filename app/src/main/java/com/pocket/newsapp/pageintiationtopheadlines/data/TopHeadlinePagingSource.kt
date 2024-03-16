package com.pocket.newsapp.pageintiationtopheadlines.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pocket.newsapp.network.NetworkService
import com.pocket.newsapp.topheadlines.data.entity.ApiArticle
import com.pocket.newsapp.utils.AppConstants.COUNTRY
import com.pocket.newsapp.utils.AppConstants.INITIAL_PAGE
import com.pocket.newsapp.utils.AppConstants.PAGE_SIZE

import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class TopHeadlinePagingSource(private val networkService: NetworkService) :
    PagingSource<Int, ApiArticle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val response = networkService.getTopHeadlines(
                country = COUNTRY,
                page = page,
                pageSize = PAGE_SIZE
            )

            LoadResult.Page(
                data = response.apiArticles,
                prevKey = if (page == INITIAL_PAGE) null else page.minus(1),
                nextKey = if (response.apiArticles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}