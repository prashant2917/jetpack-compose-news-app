package com.pocket.newsapp.newssource.data.model

import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(
    @SerializedName("status") val status: String = "",
    @SerializedName("sources") val newsSource: List<APINewsSource> = arrayListOf(),
)
