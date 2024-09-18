package com.winenote.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.internal.WineBasicTextField
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme

@Composable
fun WineTextField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = WineTheme.typography.regular14,
    hintValue: String = "",
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        errorTextColor = MaterialTheme.colorScheme.error,
        errorContainerColor = MaterialTheme.colorScheme.onBackground,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ),
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLine: Int = 1,
    maxLength: Int = 150,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    focusManager: FocusManager = LocalFocusManager.current,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    minHeight: Dp = OutlinedTextFieldDefaults.MinWidth,
    onValueChange: (String) -> Unit,
    isClearFocus: Boolean = true
) {
    val currentText by rememberUpdatedState(newValue = value)
    var dummyText by rememberSaveable { mutableStateOf(currentText) }

    val onTextTriggered = {
        if (isClearFocus) focusManager.clearFocus()
        onValueChange(value)
    }

    if (currentText.isEmpty() && dummyText.isNotEmpty()) {
        dummyText = ""
    }

    WineBasicTextField(
        modifier = modifier.onKeyEvent {
            val pressedEnter = (it.key == Key.Enter)
            if (pressedEnter) onTextTriggered()
            pressedEnter
        },
        value = dummyText,
        onValueChange = { changeValue ->
            dummyText = changeValue.take(maxLength)
            onValueChange(dummyText)
        },
        placeholder = {
            Text(
                text = hintValue,
                style = textStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        textStyle = textStyle,
        isError = isError,
        colors = colors,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        maxLines = maxLine,
        minHeight = minHeight,
        paddingValues = paddingValues,
        singleLine = singleLine,
    )
}

@ThemePreviews
@Composable
private fun WineTextFieldPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            WineTextField(
                value = "Sample",
                textStyle = WineTheme.typography.regular14,
                onValueChange = {},
                isError = false
            )
        }
    }
}