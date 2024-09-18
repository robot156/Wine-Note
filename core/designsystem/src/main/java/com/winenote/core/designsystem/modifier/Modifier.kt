package com.winenote.core.designsystem.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.delay

@ExperimentalFoundationApi
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.onLongClick(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    onClick: () -> Unit = {},
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "combinedClickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
        properties["onDoubleClick"] = onDoubleClick
        properties["onLongClick"] = onLongClick
        properties["onLongClickLabel"] = onLongClickLabel
    }
) {
    Modifier.combinedClickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        onClick = onClick,
        role = role,
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() }
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.singleClickable(
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    delay: Long = DEFAULT_SINGLE_CLICK_DURATION,
    onClick: (() -> Unit),
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "singleClickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    var enableAgain by remember { mutableStateOf(true) }

    LaunchedEffect(enableAgain) {
        if (enableAgain) return@LaunchedEffect
        delay(timeMillis = delay)
        enableAgain = true
    }

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = {
            if (enableAgain) {
                enableAgain = false
                onClick()
            }
        },
        role = role,
        indication = indication,
        interactionSource = interactionSource
    )
}

fun Modifier.singleClickable(
    bounded: Boolean = true,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    delay: Long = DEFAULT_SINGLE_CLICK_DURATION,
    onClick: () -> Unit,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "singleClickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    singleClickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        delay = delay,
        indication = ripple(bounded),
        interactionSource = remember { MutableInteractionSource() }
    )
}

private const val DEFAULT_SINGLE_CLICK_DURATION = 500L
