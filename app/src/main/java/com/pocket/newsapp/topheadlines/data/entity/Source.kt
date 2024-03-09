package com.pocket.newsapp.topheadlines.data.entity

import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo(name = "sourceId") val id: String?,
    @ColumnInfo(name = "sourceName") val name: String = ""
)