package com.pocket.newsapp.topheadlines.data.entity

data class Language(
    val id: String? = null, val name: String =""
)

data class SelectionState(
    val selectedLanguage: List<Language> = emptyList()
)