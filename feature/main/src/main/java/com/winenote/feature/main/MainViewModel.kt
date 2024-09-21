package com.winenote.feature.main

import androidx.lifecycle.viewModelScope
import com.winenote.core.domain.usecase.setting.GetThemeUseCase
import com.winenote.core.model.ThemeConfig
import com.winenote.core.model.findTheme
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    getThemeUseCase: GetThemeUseCase
) : BaseViewModel() {

    private val currentTheme = getThemeUseCase(Unit)
        .map { theme -> theme.findTheme() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ThemeConfig.FOLLOW_SYSTEM)

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Main())
    val mainUiState: StateFlow<MainUiState> = _mainUiState

    init {
        viewModelScope.launch {
            currentTheme.collect { theme ->
                _mainUiState.update { MainUiState.Main(currentTheme = theme) }
            }
        }
    }
}

sealed interface MainUiState : UiState {
    data class Main(
        val currentTheme: ThemeConfig = ThemeConfig.FOLLOW_SYSTEM
    ) : MainUiState
}