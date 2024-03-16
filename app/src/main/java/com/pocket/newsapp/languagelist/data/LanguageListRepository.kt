package com.pocket.newsapp.languagelist.data


import com.pocket.newsapp.topheadlines.data.entity.Language
import com.pocket.newsapp.utils.AppConstants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class LanguageListRepository @Inject constructor() {

    fun getLanguages(): Flow<List<Language>> {
        return flow { emit(AppConstants.LANGUAGES) }
    }

}