package com.winenote.feature.winewrite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.WineTextField
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R

@Composable
fun WineWriteTextField(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    value: String,
    hintText: String,
    maxLine : Int = 1,
    maxLength: Int = 50,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = WineTheme.typography.bold14,
                color = MaterialTheme.colorScheme.onSurface,
            )

            if (description != null) {
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = description,
                    style = WineTheme.typography.bold12,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        WineTextField(
            modifier = modifier,
            value = value,
            hintValue = hintText,
            maxLine = maxLine,
            maxLength = maxLength,
            imeAction = imeAction,
            keyboardType = keyboardType,
            minHeight = 0.dp,
            paddingValues = PaddingValues(vertical = 4.dp),
            onValueChange = onValueChange,
        )
    }
}

@ThemePreviews
@Composable
private fun WineWriteTextFieldPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {
            WineWriteTextField(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.write_wine_name),
                value = "",
                hintText = stringResource(id = R.string.write_wine_name_hint),
                onValueChange = {}
            )
        }
    }
}