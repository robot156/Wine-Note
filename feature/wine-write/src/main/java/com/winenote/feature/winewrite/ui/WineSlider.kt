package com.winenote.feature.winewrite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme

@Composable
fun WineSlider(
    modifier: Modifier = Modifier,
    title: String = "",
    currentPosition: Float = 0f,
    onChangePosition: (Float) -> Unit = {},
    valueRange: ClosedFloatingPointRange<Float> = 1f..5f,
    steps: Int = 3,
    valueStartText: String,
    valueEndText: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = WineTheme.typography.bold14,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = currentPosition,
            onValueChange = onChangePosition,
            valueRange = valueRange,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                inactiveTickColor = Color.White,
            ),
            thumb = {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                )
            },
            steps = steps
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier,
                text = valueStartText,
                style = WineTheme.typography.regular12,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier,
                text = valueEndText,
                style = WineTheme.typography.regular12,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
@ThemePreviews
private fun WineSliderPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            WineSlider(
                currentPosition = 3f,
                title = "향의 세기",
                valueStartText = "낮은",
                valueEndText = "높은"
            )
        }
    }
}