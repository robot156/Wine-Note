package com.winenote.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winenote.core.ui.state.UiEvent
import com.winenote.core.ui.util.EventFlow
import com.winenote.core.ui.util.MutableEventFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    // Used to re-run flows on command
    private val refreshSignal = MutableSharedFlow<Unit>()

    // Used to run flows on init and also on command
    protected val loadDataSignal: Flow<Unit> = flow {
        emit(Unit)
        emitAll(refreshSignal)
    }

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _uiEvent = MutableEventFlow<UiEvent>()
    val uiEvent: EventFlow<UiEvent> = _uiEvent

    protected fun onRefresh() = viewModelScope.launch {
        refreshSignal.emit(Unit)
    }

    protected fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }

    protected fun setUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(uiEvent)
        }
    }
}