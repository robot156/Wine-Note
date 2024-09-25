package com.winenote.feature.winelist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.WineFloatingButton
import com.winenote.core.designsystem.component.WineScaffold
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.core.ui.util.ObserveAsEvents
import com.winenote.feature.winelist.model.WineListUiModel
import com.winenote.feature.winelist.ui.WineListContent
import com.winenote.feature.winelist.ui.WineListTopAppbar
import kotlinx.coroutines.flow.flowOf

internal typealias OnWineListUiAction = (WineListUiAction) -> Unit

@Composable
internal fun WineListRoute(
    viewModel: WineListViewModel = hiltViewModel(),
    navigateToWineDetail: (String) -> Unit,
    navigateToWineWrite: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    val wineRecords = viewModel
        .wineRecords
        .collectAsLazyPagingItems(LocalLifecycleOwner.current.lifecycleScope.coroutineContext)

    ObserveAsEvents(flow = viewModel.uiEvent) {
        when (it) {
            is WineListUiEvent.NavigateToWineDetail -> navigateToWineDetail(it.recordId)
            is WineListUiEvent.NavigateToWrite -> navigateToWineWrite()
            is WineListUiEvent.NavigateToSetting -> navigateToSetting()
        }
    }

    WineListScreen(
        wineRecords = wineRecords,
        onUiAction = remember { viewModel::onUiAction }
    )
}

@Composable
fun WineListScreen(
    modifier: Modifier = Modifier,
    wineRecords: LazyPagingItems<WineListUiModel>,
    onUiAction: OnWineListUiAction = {}
) {
    WineScaffold(
        modifier = modifier,
        topBar = { WineListTopAppbar(onUiAction = onUiAction) },
        content = {
            WineListContent(
                modifier = Modifier.padding(it),
                wineRecords = wineRecords,
                onUiAction = onUiAction
            )
        },
        floatingActionButton = {
            WineFloatingButton(
                modifier = Modifier.padding(end = 18.dp, bottom = 40.dp),
                onClick = { onUiAction(WineListUiAction.OnClickWrite) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_pen),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@ThemePreviews
@Composable
private fun WineListScreenPreview() {
    WineNoteTheme {
        WineListScreen(
            wineRecords = flowOf(
                PagingData.from(
                    listOf(
                        WineListUiModel.EmptyItem,
                        WineListUiModel.WineDateHeaderItem("24.08"),
                        WineListUiModel.WineRecordItem(WineRecord(name = "그냥 와인")),
                    )
                ),
            ).collectAsLazyPagingItems(),
        )
    }
}