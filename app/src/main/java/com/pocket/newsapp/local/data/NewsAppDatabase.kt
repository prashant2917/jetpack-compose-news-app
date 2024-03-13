package com.pocket.newsapp.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pocket.newsapp.local.data.dao.SourceDao
import com.pocket.newsapp.local.data.dao.TopHeadlinesDao
import com.pocket.newsapp.topheadlines.data.entity.Article
import com.pocket.newsapp.topheadlines.data.entity.NewsSources

@Database(
    entities = [Article::class, NewsSources::class],
    version = 1,
    exportSchema = false
)
abstract class NewsAppDatabase : RoomDatabase() {

    abstract fun topHeadlinesDao(): TopHeadlinesDao

    abstract fun newsSourceDao(): SourceDao

}