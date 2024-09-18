package com.winenote.feature.winelist.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.winenote.core.resource.R
import com.winenote.core.ui.util.getDateTimeFormatString
import com.winenote.feature.winelist.OnWineListUiAction
import com.winenote.feature.winelist.model.WineListUiModel
import com.winenote.feature.winelist.ui.type.WineListEmptyScreen
import com.winenote.feature.winelist.ui.type.WineListRecordDate
import com.winenote.feature.winelist.ui.type.WineListRecordItem

@Composable
fun WineListContent(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    wineRecords: LazyPagingItems<WineListUiModel>,
    onUiAction: OnWineListUiAction = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(bottom = 150.dp)
    ) {
        if (wineRecords.itemCount >= 1 && (wineRecords[0] is WineListUiModel.WineRecordItem)) {
            item {
                val date = (wineRecords[0] as WineListUiModel.WineRecordItem).record.updatedAt
                WineListRecordDate(yearAndMonth = getDateTimeFormatString(date, stringResource(id = R.string.regex_date_year_and_month)))
            }
        }

        items(
            count = wineRecords.itemCount,
            key = wineRecords.itemKey(),
            contentType = wineRecords.itemContentType()
        ) { index ->
            when (val uiModel = wineRecords[index]) {
                is WineListUiModel.EmptyItem -> WineListEmptyScreen(Modifier.fillParentMaxSize())
                is WineListUiModel.WineDateHeaderItem -> WineListRecordDate(yearAndMonth = uiModel.yearAndMonth)
                is WineListUiModel.WineRecordItem -> WineListRecordItem(wineRecordItem = uiModel, onUiAction = onUiAction)
                else -> return@items
            }
        }
    }
}