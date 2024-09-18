package com.winenote.feature.winewrite

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val wineWriteRoute = "wine_write_route"
internal const val writeArgs = "writeArgs"

internal class WriteArgs(val recordId: String? = null) {
    constructor(savedStateHandle: SavedStateHandle) : this(savedStateHandle[writeArgs])
}

fun NavGraphBuilder.wineWriteNavigation(
    navigateToWineDetail: (String, NavOptions) -> Unit,
    navigateToBack: () -> Unit
) {
    composable(
        route = wineWriteRoute.plus("/").plus("{${writeArgs}}"),
        arguments = listOf(
            navArgument(writeArgs) {
                type = NavType.StringType
                nullable = true
            }
        )
    ) {
        WineWriteRoute(
            navigateToWineDetail = navigateToWineDetail,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToWineWrite(recordId: String? = null, navOptions: NavOptions? = null) {
    navigate(wineWriteRoute.plus("/").plus(recordId), navOptions)
}