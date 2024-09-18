package com.winenote.feature.winedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.winenote.core.resource.R
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.Gray20
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme

@Composable
fun TasteRange(
    tasteText: String,
    fillValue: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tasteText,
            style = WineTheme.typography.regular12,
            color = MaterialTheme.colorScheme.onSurface
        )

        (1..5).forEach { count ->
            TasteDot(
                getTasteColor(
                    fillValue = fillValue,
                    value = count
                )
            )
        }
    }
}

@Composable
private fun getTasteColor(fillValue: Int, value: Int): Color {
    return if (fillValue >= value) {
        MaterialTheme.colorScheme.primary.copy(
            alpha = 0.5f.plus(value.div(10f))
        )
    } else {
        Gray20
    }
}

@Composable
private fun TasteDot(color: Color) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(color = color, shape = CircleShape),
    )
}

@ThemePreviews
@Composable
private fun TasteRangePreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {
            TasteRange(
                tasteText = stringResource(id = R.string.write_body),
                fillValue = 5
            )
        }
    }
}