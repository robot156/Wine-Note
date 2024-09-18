package com.winenote.feature.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val settingRoute = "setting_route"

fun NavGraphBuilder.settingNavigation(
    navigateToBinRecord: () -> Unit,
    navigateToBack: () -> Unit
) {
    composable(settingRoute) {
        SettingRoute(
            navigateToBinRecord = navigateToBinRecord,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    this.navigate(settingRoute, navOptions)
}