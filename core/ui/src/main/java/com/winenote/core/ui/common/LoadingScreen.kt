package com.winenote.core.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.winenote.core.designsystem.component.WineLottieAnimation

@Composable
fun LoadingDialog(isLoading: Boolean) {
    AnimatedVisibility(visible = isLoading) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
        ) {
            Box(modifier = Modifier) {
                WineLottieAnimation(
                    modifier = Modifier
                        .size(width = 120.dp, height = 120.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}