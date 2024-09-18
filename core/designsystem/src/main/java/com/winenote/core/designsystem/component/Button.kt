package com.winenote.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme

@Composable
fun WinePrimaryButton(
    modifier: Modifier = Modifier,
    defaultMinHeight: Dp = 40.dp,
    shapes: RoundedCornerShape = RoundedCornerShape(6.0.dp),
    text: String = "",
    style: TextStyle= WineTheme.typography.regular16,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.defaultMinSize(minHeight = defaultMinHeight),
        onClick = onClick,
        shape = shapes,
        content = {
            Text(
                text = text,
                style = style
            )
        }
    )
}

@Composable
fun WineOutlineButton(
    modifier: Modifier = Modifier,
    defaultMinHeight: Dp = 40.dp,
    shapes: RoundedCornerShape = RoundedCornerShape(6.0.dp),
    text: String = "",
    style: TextStyle= WineTheme.typography.regular16,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minHeight = defaultMinHeight),
        onClick = onClick,
        shape = shapes,
        content = {
            Text(
                text = text,
                style = style
            )
        }
    )
}

@Composable
fun WineFloatingButton(
    modifier: Modifier = Modifier,
    shapes: RoundedCornerShape = RoundedCornerShape(12.0.dp),
    shadowElevation: Dp = 6.dp,
    minSize: Dp = 56.dp,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Button },
        shape = shapes,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 0.dp,
        shadowElevation = shadowElevation
    ) {
        Box(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = minSize,
                    minHeight = minSize,
                ),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@ThemePreviews
@Composable
private fun ButtonPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WinePrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "입력"
            )

            WineOutlineButton(
                modifier = Modifier.fillMaxWidth(),
                text = "입력"
            )

            WineFloatingButton {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = ""
                )
            }
        }
    }
}