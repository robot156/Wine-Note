package com.winenote.feature.winebin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R
import com.winenote.core.ui.common.WineRecordCard
import com.winenote.feature.winebin.OnWineBinUiAction
import com.winenote.feature.winebin.WineBinUiAction
import com.winenote.feature.winebin.model.WineBinUiModel

@Composable
fun WineBinRecordItem(
    modifier: Modifier = Modifier,
    wineRecordItem: WineBinUiModel.WineRecordItem,
    onUiAction: OnWineBinUiAction = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.unit_day, wineRecordItem.deleteDay),
            style = WineTheme.typography.regular14,
            color = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            WineRecordCard(
                modifier = Modifier.fillMaxWidth(),
                record = wineRecordItem.record,
                onClickWineRecord = { onUiAction(WineBinUiAction.OnClickWineRecord(it)) }
            )
        }
    }
}