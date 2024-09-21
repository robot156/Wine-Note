package com.winenote.core.model

enum class ThemeConfig(val theme: String) {
    FOLLOW_SYSTEM("FOLLOW_SYSTEM"),
    LIGHT("LIGHT"),
    DARK("DARK"),
}

fun String.findTheme(): ThemeConfig {
    return try {
        ThemeConfig.valueOf(this)
    } catch (e: Exception) {
        ThemeConfig.FOLLOW_SYSTEM
    }
} 