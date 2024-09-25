package com.winenote.feature.winelist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winenote.core.route.Route

fun NavGraphBuilder.wineListNavigation(
    navigateToWineDetail: (String) -> Unit,
    navigateToWineWrite: () -> Unit,
    navigateToSetting: () -> Unit
) {
    composable<Route.WineList> {
        WineListRoute(
            navigateToWineDetail = navigateToWineDetail,
            navigateToWineWrite = navigateToWineWrite,
            navigateToSetting = navigateToSetting
        )
    }
}

fun NavController.navigateToWineList(navOptions: NavOptions) {
    this.navigate(Route.WineList, navOptions)
}