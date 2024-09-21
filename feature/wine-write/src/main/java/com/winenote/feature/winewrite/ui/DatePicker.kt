package com.winenote.feature.winewrite.ui

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R

@Composable
fun DatePickerDialog(
    date: Long,
    onDateSelected: (date: Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis ?: System.currentTimeMillis())
                    onDismiss()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.common_confirm),
                    style = WineTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(id = R.string.common_cancel),
                    style = WineTheme.typography.regular16,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    ) {
        WineNoteTheme(
            darkTheme = WineTheme.isSystemInDarkTheme
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors().copy(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    dayContentColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        }
    }
}