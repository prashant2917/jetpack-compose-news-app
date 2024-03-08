package com.pocket.newsapp.base.navigation

sealed class Route(val name: String) {

    object HomeScreen : Route("homescreen")

    object TopHeadline : Route("topheadline")
    object PaginationTopHeadline : Route("paginationtopheadline")

    object OfflineTopHeadline : Route("offlinetopheadline")
    object NewsSources : Route("newssources")
    object LanguageList : Route("languagelist")
    object CountryList : Route("countrylist")
    object Search : Route("search")

    object NewsList :
        Route(name = "newslist?sourceId={sourceId}&countryId={countryId}&languageId={languageId}") {
        fun passData(
            sourceId: String = "",
            countryId: String = "",
            languageId: String = ""
        ): String {
            return "newslist?sourceId=$sourceId&countryId=$countryId&languageId=$languageId"
        }
    }

}