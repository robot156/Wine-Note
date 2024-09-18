package com.winenote.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

private val LightColorScheme = darkColorScheme(
    primaryContainer = Theme_light_PrimaryContainer,
    primary = Theme_light_Primary,
    onPrimary = Theme_light_onPrimary,
    surface = Theme_light_surface,
    onSurface = Theme_light_onSurface,
    onSurfaceVariant = theme_light_onSurfaceVariant,
    background = Theme_light_background,
    onBackground = Theme_light_onBackground,
    outline = Theme_light_outline,
    error = Theme_light_error
)

private val DarkColorScheme = lightColorScheme(
    primaryContainer = Theme_dark_PrimaryContainer,
    primary = Theme_dark_Primary,
    onPrimary = Theme_dark_onPrimary,
    surface = Theme_dark_surface,
    onSurface = Theme_dark_onSurface,
    onSurfaceVariant = Theme_dark_onSurfaceVariant,
    background = Theme_dark_background,
    onBackground = Theme_dark_onBackground,
    outline = Theme_dark_outline,
    error = Theme_dark_error
)

@Composable
fun WineNoteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalDensity provides Density(density = LocalDensity.current.density, fontScale = 1f),
        LocalTypography provides Typography,
    ) {

        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object WineTheme {
    val typography: WineNoteTypography
        @Composable
        get() = LocalTypography.current
}