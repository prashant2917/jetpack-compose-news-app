package com.pocket.newsapp.topheadlines.data.model

import com.google.gson.annotations.SerializedName
import com.pocket.newsapp.topheadlines.data.entity.ApiArticle

data class TopHeadlinesResponse(
    @SerializedName("status") val status: String = "",
    @SerializedName("totalResults") val totalResults: Int = 0,
    @SerializedName("articles") val apiArticles: List<ApiArticle> = ArrayList(),
)