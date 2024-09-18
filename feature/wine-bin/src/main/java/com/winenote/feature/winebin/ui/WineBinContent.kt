package com.winenote.feature.winebin.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R
import com.winenote.feature.winebin.OnWineBinUiAction
import com.winenote.feature.winebin.model.WineBinUiModel

@Composable
fun WineBinContent(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    wineRecords: LazyPagingItems<WineBinUiModel>,
    onUiAction: OnWineBinUiAction = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(bottom = 150.dp)
    ) {
        if (wineRecords.itemCount >= 1 && (wineRecords[0] is WineBinUiModel.WineRecordItem)) {
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 20.dp),
                    text = stringResource(id = R.string.bin_description),
                    style = WineTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        items(
            count = wineRecords.itemCount,
            key = wineRecords.itemKey(),
            contentType = wineRecords.itemContentType()
        ) { index ->
            when (val uiModel = wineRecords[index]) {
                is WineBinUiModel.EmptyItem -> WineBinEmptyScreen(Modifier.fillParentMaxSize())
                is WineBinUiModel.WineRecordItem -> WineBinRecordItem(wineRecordItem = uiModel, onUiAction = onUiAction)
                else -> return@items
            }
        }
    }
}