package com.winenote.feature.winebin

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.WineAlertDialog
import com.winenote.core.designsystem.component.WineFloatingButton
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.core.ui.common.WineScaffold
import com.winenote.core.ui.util.ObserveAsEvents
import com.winenote.feature.winebin.model.WineBinUiModel
import com.winenote.feature.winebin.ui.WineBinContent
import com.winenote.feature.winebin.ui.WineBinTopAppbar
import kotlinx.coroutines.flow.flowOf

internal typealias OnWineBinUiAction = (WineBinUiAction) -> Unit

@Composable
internal fun WineBinRoute(
    viewModel: WineBinViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
    navigateToWineDetail: (String) -> Unit
) {
    val context = LocalContext.current
    val wineRecords = viewModel.wineRecords.collectAsLazyPagingItems(LocalLifecycleOwner.current.lifecycleScope.coroutineContext)
    val wineBinUiState by viewModel.wineBinUiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is WineBinUiEvent.NavigateToBack -> navigateToBack()
            is WineBinUiEvent.NavigateToWineDetail -> navigateToWineDetail(event.recordId)
            is WineBinUiEvent.ShowToastMessage -> Toast.makeText(context, event.messageRes, Toast.LENGTH_SHORT).show()
        }
    }

    WineBinScreen(
        wineRecords = wineRecords,
        uiState = wineBinUiState as WineBinUiState.WineBin,
        onUiAction = remember { viewModel::onUiAction }
    )
}

@Composable
fun WineBinScreen(
    modifier: Modifier = Modifier,
    uiState: WineBinUiState.WineBin,
    wineRecords: LazyPagingItems<WineBinUiModel>,
    onUiAction: OnWineBinUiAction = {}
) {
    WineScaffold(
        modifier = modifier,
        topBar = { WineBinTopAppbar(onUiAction = onUiAction) },
        content = {
            WineBinContent(
                modifier = Modifier.padding(it),
                wineRecords = wineRecords,
                onUiAction = onUiAction
            )
        },
        floatingActionButton = {
            if (uiState.isVisibleClearBinButton) {
                WineFloatingButton(
                    modifier = Modifier.padding(end = 18.dp, bottom = 40.dp),
                    onClick = { onUiAction(WineBinUiAction.OnShowClearDialog(true)) }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_bin),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )

    if (uiState.isShowClearDialog) {
        val dismissAction = WineBinUiAction.OnShowClearDialog(false)

        WineAlertDialog(
            title = stringResource(id = R.string.bin_clear_title),
            description = stringResource(id = R.string.bin_clear_description),
            onClickNegative = { onUiAction(dismissAction) },
            onClickPositive = { onUiAction(WineBinUiAction.OnClickClearBin) },
            onDismiss = { onUiAction(dismissAction) }
        )
    }
}

@ThemePreviews
@Composable
private fun WineBinScreenPreview() {
    WineNoteTheme {
        WineBinScreen(
            uiState = WineBinUiState.WineBin(),
            wineRecords = flowOf(
                PagingData.from(
                    listOf(
                        WineBinUiModel.EmptyItem,
                        WineBinUiModel.WineRecordItem(WineRecord(name = "그냥 와인"), deleteDay = 15),
                    )
                ),
            ).collectAsLazyPagingItems(),
        )
    }
}