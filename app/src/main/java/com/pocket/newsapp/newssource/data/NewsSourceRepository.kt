package com.pocket.newsapp.newssource.data


import com.pocket.newsapp.local.data.DatabaseService
import com.pocket.newsapp.network.NetworkService
import com.pocket.newsapp.newssource.data.model.asSource
import com.pocket.newsapp.topheadlines.data.entity.NewsSources
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class NewsSourceRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getNewsSources(): Flow<List<NewsSources>> {
        return flow { emit(networkService.getNewsSources()) }
            .map {
                it.newsSource.map { apiSource -> apiSource.asSource() }
            }.flatMapConcat { apiSource ->
                flow { emit(databaseService.deleteAndInsertAllNewsSources((apiSource))) }
            }.flatMapConcat {
                databaseService.getNewsSources()
            }
    }

    fun getNewsSourcesFromDB(): Flow<List<NewsSources>> {
        return databaseService.getNewsSources()

    }

}