package com.pocket.newsapp.topheadlines.data.entity

import com.google.gson.annotations.SerializedName

data class ApiSource(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = "",
)

fun ApiSource.toSourceEntity(): Source {
    return Source(id, name)
}