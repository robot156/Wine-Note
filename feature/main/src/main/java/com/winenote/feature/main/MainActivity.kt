package com.winenote.feature.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.model.ThemeConfig
import com.winenote.feature.main.navigation.WineNoteNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val maniViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val mainUiState by maniViewModel.mainUiState.collectAsStateWithLifecycle()
            val isDarkTheme = shouldUseDarkTheme(mainUiState)

            LaunchedEffect(isDarkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { isDarkTheme },
                    navigationBarStyle = SystemBarStyle.auto(lightScrim, darkScrim) { isDarkTheme },
                )
            }

            WineNoteTheme(
                darkTheme = shouldUseDarkTheme(mainUiState)
            ) {
                WineNoteNavHost()
            }
        }
    }

    @Composable
    private fun shouldUseDarkTheme(
        uiState: MainUiState,
    ): Boolean = when (uiState) {
        is MainUiState.Main -> when (uiState.currentTheme) {
            ThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
            ThemeConfig.LIGHT -> false
            ThemeConfig.DARK -> true
        }
    }

    private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
    private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
}