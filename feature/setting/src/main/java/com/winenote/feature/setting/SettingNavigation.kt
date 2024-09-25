package com.winenote.feature.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winenote.core.route.Route

fun NavGraphBuilder.settingNavigation(
    navigateToBinRecord: () -> Unit,
    navigateToBack: () -> Unit
) {
    composable<Route.Setting> {
        SettingRoute(
            navigateToBinRecord = navigateToBinRecord,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    this.navigate(Route.Setting, navOptions)
}