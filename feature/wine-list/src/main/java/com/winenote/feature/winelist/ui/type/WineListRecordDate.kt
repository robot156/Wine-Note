package com.winenote.feature.winelist.ui.type

import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme

@Composable
fun WineListRecordDate(
    yearAndMonth: String,
) {
    Box(
        modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = yearAndMonth,
            style = WineTheme.typography.regular14,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@ThemePreviews
@Composable
private fun WineListRecordDatePreview() {
    WineNoteTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            WineListRecordDate(yearAndMonth = "24.04")
        }
    }
}