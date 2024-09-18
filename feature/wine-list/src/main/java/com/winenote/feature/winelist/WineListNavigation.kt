package com.winenote.feature.winelist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val wineListRoute = "wine_list_route"

fun NavGraphBuilder.wineListNavigation(
    navigateToWineDetail: (String) -> Unit,
    navigateToWineWrite: () -> Unit,
    navigateToSetting: () -> Unit
) {
    composable(wineListRoute) {
        WineListRoute(
            navigateToWineDetail = navigateToWineDetail,
            navigateToWineWrite = navigateToWineWrite,
            navigateToSetting = navigateToSetting
        )
    }
}

fun NavController.navigateToWineList(navOptions: NavOptions) {
    this.navigate(wineListRoute, navOptions)
}