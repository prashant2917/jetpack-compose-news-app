package com.pocket.newsapp.languagelist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.newsapp.base.ui.state.UiState
import com.pocket.newsapp.languagelist.data.LanguageListRepository
import com.pocket.newsapp.topheadlines.data.entity.Language
import com.pocket.newsapp.utils.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageListViewModel @Inject constructor(
    private val languageListRepository: LanguageListRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _languageUiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val languageUiState: StateFlow<UiState<List<Language>>> = _languageUiState

    init {
        fetchLanguages()
    }

    fun fetchLanguages() {
        viewModelScope.launch(dispatcherProvider.main) {
            languageListRepository.getLanguages()
                .flowOn(dispatcherProvider.default)
                .catch { e ->
                    _languageUiState.value = UiState.Error(e.toString())
                }.collect {
                    _languageUiState.value = UiState.Success(it)
                }
        }
    }
}