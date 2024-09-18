package com.winenote.feature.winedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.model.WineRecord
import com.winenote.core.ui.common.EmptyScreen
import com.winenote.core.ui.common.WineScaffold
import com.winenote.core.ui.util.ObserveAsEvents
import com.winenote.feature.winedetail.ui.WineDetailInformation
import com.winenote.feature.winedetail.ui.WineDetailTasteRanges
import com.winenote.feature.winedetail.ui.WineDetailTopAppbar
import com.winenote.feature.winedetail.ui.WineDetailTotalComment
import com.winenote.feature.winedetail.ui.edit.WineDetailEditDialog

internal typealias OnWineDetailUiAction = (WineDetailUiAction) -> Unit

@Composable
internal fun WineDetailRoute(
    viewModel: WineDetailViewModel = hiltViewModel(),
    navigateToWineWrite: (String) -> Unit,
    navigateToBack: () -> Unit,
) {
    val wineDetailUiState by viewModel.wineDetailUiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is WineDetailEvent.NavigateToBack -> navigateToBack()
            is WineDetailEvent.NavigateToWineWrite -> navigateToWineWrite(event.recordId)
        }
    }

    when (val uiState = wineDetailUiState) {
        is WineDetailUiState.None -> EmptyScreen()
        is WineDetailUiState.WineDetail -> WineDetailScreen(
            uiState = uiState,
            onUiAction = remember { viewModel::onUiAction }
        )
    }
}

@Composable
fun WineDetailScreen(
    modifier: Modifier = Modifier,
    uiState: WineDetailUiState.WineDetail,
    onUiAction: OnWineDetailUiAction,
) {
    WineScaffold(
        modifier = modifier,
        topBar = { WineDetailTopAppbar(onUiAction = onUiAction) },
        content = {
            WineDetailContainer(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                uiState = uiState
            )
        }
    )

    if (uiState.isShowEditDialog) {
        WineDetailEditDialog(
            isDeleted = uiState.record.isDelete,
            onUiAction = onUiAction
        )
    }
}

@Composable
fun WineDetailContainer(
    modifier: Modifier = Modifier,
    uiState: WineDetailUiState.WineDetail,
) {
    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        WineDetailInformation(wineRecord = uiState.record)
        WineDetailTasteRanges(wineRecord = uiState.record)
        WineDetailTotalComment(wineRecord = uiState.record)
    }
}

@ThemePreviews
@Composable
private fun WineDetailScreenPreview() {
    WineNoteTheme {
        WineDetailScreen(
            uiState = WineDetailUiState.WineDetail(
                record = WineRecord(
                    name = "8월의 와인",
                    region = "캘리포니아",
                    grapeVariety = "소비뇽",
                    scentFeel = "열대 과일 향이 난다.",
                    comment = "혼술하기 좋음"
                )
            ),
            onUiAction = {}
        )
    }
}