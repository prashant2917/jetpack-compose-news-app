package com.pocket.newsapp.countrylist.data


import com.pocket.newsapp.topheadlines.data.entity.Country
import com.pocket.newsapp.utils.AppConstants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class CountryListRepository @Inject constructor() {

    fun getCountry(): Flow<List<Country>> {
        return flow { emit(AppConstants.COUNTRIES) }
    }
}