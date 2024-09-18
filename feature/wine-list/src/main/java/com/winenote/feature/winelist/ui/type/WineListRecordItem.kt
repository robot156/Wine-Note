package com.winenote.feature.winelist.ui.type

import androidx.compose.foundation.background
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
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.core.ui.common.WineRecordCard
import com.winenote.core.ui.util.getDateTimeFormatString
import com.winenote.feature.winelist.OnWineListUiAction
import com.winenote.feature.winelist.WineListUiAction
import com.winenote.feature.winelist.model.WineListUiModel

@Composable
fun WineListRecordItem(
    modifier: Modifier = Modifier,
    wineRecordItem: WineListUiModel.WineRecordItem,
    onUiAction: OnWineListUiAction = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = getDateTimeFormatString(wineRecordItem.record.updatedAt, stringResource(id = R.string.regex_date_day)),
            style = WineTheme.typography.regular14,
            color = MaterialTheme.colorScheme.onSurface
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            WineRecordCard(
                modifier = Modifier.fillMaxWidth(),
                record = wineRecordItem.record,
                onClickWineRecord = { onUiAction(WineListUiAction.OnClickWineRecord(it)) }
            )
        }
    }
}

@ThemePreviews
@Composable
private fun WineListRecordItemPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 10.dp)
        ) {
            WineListRecordItem(
                wineRecordItem = WineListUiModel.WineRecordItem(
                    WineRecord(
                        name = "코다치 로쏘 스위트",
                        score = 4.0f
                    )
                )
            )
        }
    }
}