package com.winenote.feature.winedetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val wineDetailRoute = "wine_detail_route"
internal const val detailArgs = "detailArgs"

internal class DetailArgs(val recordId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle[detailArgs]).toString())
}

fun NavGraphBuilder.wineDetailNavigation(
    navigateToWineWrite: (String) -> Unit,
    navigateToBack: () -> Unit
) {
    composable(
        route = wineDetailRoute.plus("/").plus("{${detailArgs}}"),
        arguments = listOf(
            navArgument(detailArgs) {
                type = NavType.StringType
            }
        )
    ) {
        WineDetailRoute(
            navigateToWineWrite = navigateToWineWrite,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToWineDetail(recordId: String, navOptions: NavOptions? = null) {
    navigate(wineDetailRoute.plus("/").plus(recordId), navOptions)
}