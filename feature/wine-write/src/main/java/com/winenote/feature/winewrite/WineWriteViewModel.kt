package com.winenote.feature.winewrite

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.winenote.core.domain.Result
import com.winenote.core.domain.asResult
import com.winenote.core.domain.data
import com.winenote.core.domain.usecase.record.GetWineRecordUseCase
import com.winenote.core.domain.usecase.record.InsertWineRecordUseCase
import com.winenote.core.domain.usecase.record.UpdateWineRecordUseCase
import com.winenote.core.model.WineColor
import com.winenote.core.model.WineRecord
import com.winenote.core.model.asEntity
import com.winenote.core.model.asItem
import com.winenote.core.resource.R
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiAction
import com.winenote.core.ui.state.UiEvent
import com.winenote.core.ui.state.UiState
import com.winenote.core.ui.state.checkState
import com.winenote.core.ui.util.getDateTimeFormatString
import com.winenote.core.ui.util.getLongFormatZonedDateTime
import com.winenote.core.ui.util.isFloat
import com.winenote.feature.winewrite.state.WriteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
internal class WineWriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getWineRecordUseCase: GetWineRecordUseCase,
    private val insertWineRecordUseCase: InsertWineRecordUseCase,
    private val updateWineRecordUseCase: UpdateWineRecordUseCase,
) : BaseViewModel() {

    private val writeArgs = WriteArgs(savedStateHandle)

    private val _wineWriteUiState = MutableStateFlow<WineWriteUiState>(WineWriteUiState.None)
    val wineWriteUiState: StateFlow<WineWriteUiState> = _wineWriteUiState

    init {
        viewModelScope.launch {
            val uiState = writeArgs.recordId
                ?.let {
                    getWineRecordUseCase(GetWineRecordUseCase.Params(it)).data
                        ?.asItem()
                        ?.let { wineRecord -> WineWriteUiState.WineWrite(record = wineRecord) }
                }
                ?: WineWriteUiState.WineWrite()

            _wineWriteUiState.update { uiState }
        }
    }

    fun onUiAction(uiAction: WineWriteUiAction) {
        when (uiAction) {
            is WineWriteUiAction.OnClickBack -> setPageMove(isPrevious = true)
            is WineWriteUiAction.OnClickNext -> setPageMove(isPrevious = false)

            // 정보
            is WineWriteUiAction.OnChangeRecordDate -> setRecordDate(uiAction.date)
            is WineWriteUiAction.OnChangeWineName -> setWineName(uiAction.name)
            is WineWriteUiAction.OnChangeWineRegion -> setWineRegion(uiAction.region)
            is WineWriteUiAction.OnChangeWineAlcohol -> setWineAlcohol(uiAction.alcohol)
            is WineWriteUiAction.OnChangeWineGrapeVariety -> setWineGrapeVariety(uiAction.grapeVariety)
            is WineWriteUiAction.OnShowDatePickerDialog -> showDatePickerDialog(uiAction.isShow)

            // 색과 향
            is WineWriteUiAction.OnClickWineColor -> setWineColor(uiAction.color)
            is WineWriteUiAction.OnChangeWineScent -> setWineScent(uiAction.scent)
            is WineWriteUiAction.OnChangeWineScentFeel -> setWineScentFeel(uiAction.scentFeel)

            // 맛
            is WineWriteUiAction.OnChangeWineBody -> setWineBody(uiAction.body)
            is WineWriteUiAction.OnChangeWineTannin -> setWineTannin(uiAction.tannin)
            is WineWriteUiAction.OnChangeWineSugarContent -> setWineSugarContent(uiAction.sugarContent)
            is WineWriteUiAction.OnChangeWineAcidity -> setWineAcidity(uiAction.acidity)

            // 종합평
            is WineWriteUiAction.OnChangeWineScore -> setWineScore(uiAction.score)
            is WineWriteUiAction.OnChangeWineComment -> setWineComment(uiAction.comment)
            is WineWriteUiAction.OnChangeWinePhoto -> setWinePhoto(uiAction.url)
            is WineWriteUiAction.OnNavigatePhotoPicker -> navigateToPhotoPicker()
            is WineWriteUiAction.OnClickSave -> saveWineRecord()
        }
    }

    private fun setPageMove(isPrevious: Boolean = true) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            if (currentState == WriteScreenState.Information && isPrevious) {
                setUiEvent(WineWriteUiEvent.NavigateToBack)
            } else {
                _wineWriteUiState.update {
                    val state = when (currentState) {
                        WriteScreenState.Information -> WriteScreenState.ColorScant
                        WriteScreenState.ColorScant -> if (isPrevious) WriteScreenState.Information else WriteScreenState.Taste
                        WriteScreenState.Taste -> if (isPrevious) WriteScreenState.ColorScant else WriteScreenState.Total
                        WriteScreenState.Total -> WriteScreenState.Taste
                    }

                    copy(currentIndex = currentIndex.plus(if (isPrevious) -1 else 1), currentState = state)
                }
            }
        }
    }

    private fun setRecordDate(date: Long) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                val recordAt = getLongFormatZonedDateTime(date).toString()
                copy(record = record.copy(createdAt = if (writeArgs.recordId == null) recordAt else record.createdAt, updatedAt = recordAt))
            }
        }
    }

    private fun setWineName(name: String) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(record = record.copy(name = name.trim()))
            }
        }
    }

    private fun setWineRegion(region: String) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(record = record.copy(region = region.trim()))
            }
        }
    }

    private fun setWineAlcohol(alcohol: String) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            val alcoholPercent = if (alcohol.isEmpty() || !alcohol.isFloat()) 0f else alcohol.toFloat()

            _wineWriteUiState.update {
                copy(record = record.copy(alcohol = alcoholPercent))
            }
        }
    }

    private fun setWineGrapeVariety(grapeVariety: String) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(record = record.copy(grapeVariety = grapeVariety.trim()))
            }
        }
    }

    private fun setWineColor(color: WineColor) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            if (record.color == color) return@checkState

            _wineWriteUiState.update {
                copy(record = record.copy(color = color))
            }
        }
    }

    private fun setWineScent(scent: Float) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            if (scent == record.scent) return@checkState

            _wineWriteUiState.update {
                copy(record = record.copy(scent = scent))
            }
        }
    }

    private fun setWineScentFeel(scentFeel: String) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(record = record.copy(scentFeel = scentFeel))
            }
        }
    }

    private fun setWineBody(body: Float) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            if (body == record.body) return@checkState

            _wineWriteUiState.update {
                copy(record = record.copy(body = body))
            }
        }
    }


    private fun setWineTannin(tannin: Float) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            if (tannin == record.tannin) return@checkState

            _wineWriteUiState.update {
                copy(record = record.copy(tannin = tannin))
            }
        }
    }

    private fun setWineSugarContent(sugarContent: Float) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            if (sugarContent == record.sugarContent) return@checkState

            _wineWriteUiState.update {
                copy(record = record.copy(sugarContent = sugarContent))
            }
        }
    }

    private fun setWineAcidity(acidity: Float) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            if (acidity == record.acidity) return@checkState

            _wineWriteUiState.update {
                copy(record = record.copy(acidity = acidity))
            }
        }
    }

    private fun setWineScore(score: Float) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(record = record.copy(score = score))
            }
        }
    }

    private fun setWineComment(comment: String) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(record = record.copy(comment = comment.trim()))
            }
        }
    }

    private fun setWinePhoto(photoUrl: String?) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(record = record.copy(photoUrl = photoUrl))
            }
        }
    }

    private fun showDatePickerDialog(isShow: Boolean) {
        wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
            _wineWriteUiState.update {
                copy(isShowDatePickerDialog = isShow)
            }
        }
    }

    private fun navigateToPhotoPicker() {
        setUiEvent(WineWriteUiEvent.NavigateToPhotoPicker)
    }

    private fun saveWineRecord() {
        viewModelScope.launch {
            wineWriteUiState.checkState<WineWriteUiState.WineWrite> {
                val wineRecord = record.copy(
                    name = record.name.ifBlank { getDateTimeFormatString(record.updatedAt, "M월의 와인") },
                    comment = record.comment.ifBlank { "^ㅁ^" }
                ).asEntity()

                if (writeArgs.recordId == null) {
                    insertWineRecordUseCase(InsertWineRecordUseCase.Params(wineRecord))
                } else {
                    updateWineRecordUseCase(UpdateWineRecordUseCase.Params(wineRecord))
                }.asResult().onEach { setLoading(it is Result.Loading) }.collect { result ->
                    when (result) {
                        is Result.Loading -> return@collect
                        is Result.Success -> setUiEvent(WineWriteUiEvent.NavigateToWineDetail(record.id))
                        is Result.Error -> setUiEvent(WineWriteUiEvent.ShowToastMessage(R.string.write_error_message))
                    }
                }
            }
        }
    }
}

sealed interface WineWriteUiState : UiState {
    data class WineWrite(
        val record: WineRecord = WineRecord(id = UUID.randomUUID().toString()),
        val currentState: WriteScreenState = WriteScreenState.Information,
        val currentIndex: Int = 1,
        val totalIndex: Int = 4,
        val isShowDatePickerDialog: Boolean = false,
    ) : WineWriteUiState

    data object None : WineWriteUiState
}

sealed interface WineWriteUiAction : UiAction {
    data object OnClickBack : WineWriteUiAction
    data object OnClickNext : WineWriteUiAction
    data object OnClickSave : WineWriteUiAction

    data class OnChangeRecordDate(val date: Long) : WineWriteUiAction
    data class OnChangeWineName(val name: String) : WineWriteUiAction
    data class OnChangeWineRegion(val region: String) : WineWriteUiAction
    data class OnChangeWineAlcohol(val alcohol: String) : WineWriteUiAction
    data class OnChangeWineGrapeVariety(val grapeVariety: String) : WineWriteUiAction
    data class OnShowDatePickerDialog(val isShow: Boolean) : WineWriteUiAction

    data class OnClickWineColor(val color: WineColor) : WineWriteUiAction
    data class OnChangeWineScent(val scent: Float) : WineWriteUiAction
    data class OnChangeWineScentFeel(val scentFeel: String) : WineWriteUiAction

    data class OnChangeWineBody(val body: Float) : WineWriteUiAction
    data class OnChangeWineTannin(val tannin: Float) : WineWriteUiAction
    data class OnChangeWineSugarContent(val sugarContent: Float) : WineWriteUiAction
    data class OnChangeWineAcidity(val acidity: Float) : WineWriteUiAction

    data class OnChangeWineScore(val score: Float) : WineWriteUiAction
    data class OnChangeWineComment(val comment: String) : WineWriteUiAction
    data class OnChangeWinePhoto(val url: String?) : WineWriteUiAction
    data class OnNavigatePhotoPicker(val isShow: Boolean) : WineWriteUiAction

}

sealed interface WineWriteUiEvent : UiEvent {
    data object NavigateToBack : WineWriteUiEvent
    data object NavigateToPhotoPicker : WineWriteUiEvent
    data class NavigateToWineDetail(val recordId: String) : WineWriteUiEvent
    data class ShowToastMessage(@StringRes val messageRes: Int) : WineWriteUiEvent
}